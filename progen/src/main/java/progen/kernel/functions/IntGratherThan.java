package progen.kernel.functions;

import java.util.List;
import java.util.Map;

import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * Clase que implementa el operador de mayor que (>) de dos variable enteras.
 * 
 * @author jirsis
 * @since 2.0
 */
public class IntGratherThan extends NonTerminal {

  private static final long serialVersionUID = 8034143990941153779L;

  /**
   * Constructor por defecto.
   */
  public IntGratherThan() {
    super("boolean$$int$$int", ">");
  }
  
  @Override
  public Object evaluate(List<Node> arguments, UserProgram userProgram, Map<String, Object> returnAddr) {
    final Integer operador1 = (Integer) arguments.get(0).evaluate(userProgram, returnAddr);
    final Integer operador2 = (Integer) arguments.get(1).evaluate(userProgram, returnAddr);
    return operador1 > operador2;
  }

}
