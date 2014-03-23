/**
 * 
 */
package progen.roles.distributed;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import progen.context.ProGenContext;
import progen.roles.Dispatcher;
import progen.roles.standalone.ClientLocal;

/**
 * @author jirsis
 *
 */
public class ClientDistributed extends ClientLocal {

    
    public ClientDistributed(){
	super();
    }
    
    @Override
    public void start() {
	super.start();	
    }
    
    @Override
    public Dispatcher initDispatcher() {
	DispatcherDistributed dispatcher = null;
	try {
	    DispatcherRemote remote= (DispatcherRemote) Naming.lookup(getDispatcherAddress());
	    dispatcher = new DispatcherDistributed(remote);
	} catch (MalformedURLException e) {
	    throw new ProGenDistributedException(getDispatcherAddress());
	} catch (RemoteException e) {
	    throw new ProGenDistributedException(getDispatcherAddress());
	} catch (NotBoundException e) {	   
	    throw new ProGenDistributedException(getDispatcherAddress());
	}
	return dispatcher;
    }
    
    private String getDispatcherAddress() {
	StringBuilder dispatcherAddress =new StringBuilder(32);
	dispatcherAddress.append("rmi://");
	dispatcherAddress.append(ProGenContext.getOptionalProperty("progen.role.client.dispatcher.bindAddress", "127.0.0.1"));
	dispatcherAddress.append(":");
	dispatcherAddress.append(ProGenContext.getOptionalProperty("progen.role.client.dispatcher.port", Registry.REGISTRY_PORT));
	dispatcherAddress.append("/");
	dispatcherAddress.append(ProGenContext.getOptionalProperty("progen.role.client.dispatcher.name", DispatcherDistributed.DISPATCHER_NAME));
	return dispatcherAddress.toString();
    }
}