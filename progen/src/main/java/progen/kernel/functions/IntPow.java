/**
 * 
 */
package progen.kernel.functions;

import java.util.List;
import java.util.Map;

import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * Implementación del operador de exponenciación entera, es decir, eleva un
 * número entero a otro número entero, devolviendo un número entero.
 * 
 * @author jirsis
 * @since 2.0
 */
public class IntPow extends NonTerminal {

  private static final long serialVersionUID = 7695120111914304103L;

  /**
   * Constructor por defecto.
   */
  public IntPow() {
    super("double$$int$$int", "^");
  }

  @Override
  public Object evaluate(List<Node> arguments, UserProgram userProgram, Map<String, Object> returnAddr) {
    final Integer base = (Integer) arguments.get(0).evaluate(userProgram, returnAddr);
    final Integer exponent = (Integer) arguments.get(1).evaluate(userProgram, returnAddr);
    return Math.pow(base, exponent);
  }

}
