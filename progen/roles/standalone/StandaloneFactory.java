package progen.roles.standalone;

import progen.context.ProGenContext;
import progen.roles.Client;
import progen.roles.Dispatcher;
import progen.roles.ExecutionRole;
import progen.roles.ProGenFactory;
import progen.roles.Role;
import progen.roles.UnknownRoleException;
import progen.roles.UnknownRoleImplementationException;
import progen.roles.Worker;

/**
 * Fábrica concreta en la que todos los roles que se pueden generar, son de tipo
 * Local, es decir, todos los componentes interactuan a través de los mecanismos
 * habituales de las llamadas a métodos del lenguaje.
 * 
 * @author jirsis
 * @since 2.0
 */
public class StandaloneFactory extends ProGenFactory {

    /*
     * (non-Javadoc)
     * 
     * @see progen.roles.ProGenFactory#makeClient()
     */
    @Override
    public Client makeClient() {
	String roleClass = ProGenContext.getOptionalProperty("progen.role.client.class", "ClientLocal");
	 
	return (Client) loadRole(roleClass);
    }

    /*
     * (non-Javadoc)
     * 
     * @see progen.roles.ProGenFactory#makeDispatcher()
     */
    @Override
    public Dispatcher makeDispatcher() {
	String roleClass = ProGenContext.getOptionalProperty("progen.role.dispatcher.class", "DispatcherLocal"); 
	return (Dispatcher)loadRole(roleClass);
    }

    /*
     * (non-Javadoc)
     * 
     * @see progen.roles.ProGenFactory#makeWorker()
     */
    @Override
    public Worker makeWorker() {
	String roleClass = ProGenContext.getOptionalProperty("progen.role.worker.class", "WorkerLocal");
	return (Worker) loadRole(roleClass);
    }
    

    private Object loadRole(String clazz){
	Object role=null;
	String roleName = "progen.roles.standalone." + clazz;
	try {
	    role = Class.forName(roleName).newInstance();
	} catch (Exception e) {
	    throw new UnknownRoleImplementationException(roleName);
	}
	return role;
    }
    

    /*
     * (non-Javadoc)
     * 
     * @see progen.roles.ProGenFactory#makeExecutionRole()
     */
    @Override
    public ExecutionRole makeExecutionRole() {
	ExecutionRole exec = null;
	String element = ProGenContext.getOptionalProperty(
		"progen.role", Role.CLIENT.name());
	try{
	    Role executionRole = Role.valueOf(element.toUpperCase());
	    switch (executionRole) {
	    case CLIENT:
		exec = this.makeClient();
		break;
	    case DISPATCHER:
		exec = this.makeDispatcher();
		break;
	    case WORKER:
		exec = this.makeWorker();
		break;
	    }
	}catch(IllegalArgumentException e){
	    throw new UnknownRoleException(element);
	}
	return exec;
    }

}
