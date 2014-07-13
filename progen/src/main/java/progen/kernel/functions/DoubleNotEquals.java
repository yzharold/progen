package progen.kernel.functions;

import java.util.HashMap;
import java.util.List;

import progen.kernel.functions.NonTerminal;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * Clase que implementa el operador de desigualdad de dos números reales.
 * 
 * @author jirsis
 * @since 2.0
 */
public class DoubleNotEquals extends NonTerminal {

  private static final long serialVersionUID = -5099771874738313272L;

  /**
   * Constructor por defecto.
   */
  public DoubleNotEquals() {
    super("boolean$$double$$double", "!=");
  }

  @Override
  public Object evaluate(List<Node> arguments, UserProgram userProgram, HashMap<String, Object> returnAddr) {
    Double operador1 = (Double) arguments.get(0).evaluate(userProgram, returnAddr);
    Double operador2 = (Double) arguments.get(1).evaluate(userProgram, returnAddr);
    return operador1.doubleValue() != operador2.doubleValue();
  }

}
