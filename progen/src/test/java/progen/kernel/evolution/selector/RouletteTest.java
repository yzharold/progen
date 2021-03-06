package progen.kernel.evolution.selector;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import progen.context.ProGenContext;
import progen.kernel.population.Individual;
import progen.kernel.population.Population;
import progen.userprogram.UserProgram;

public class RouletteTest {

  private Roulette selector;

  @Before
  public void setUp() throws Exception {
    selector = new Roulette();
  }

  @Test@Ignore
  public void testSelect() {
    ProGenContext.makeInstance();
    ProGenContext.setProperty("progen.total.RPB", "1");
    ProGenContext.setProperty("progen.RPB0.functionSet", "0");
    ProGenContext.setProperty("progen.functionSet0.return", "double");
    ProGenContext.setProperty("progen.functionSet0", "DoubleX, DoubleY, DoublePlus, DoubleMinus");
    ProGenContext.setProperty("progen.population.init-mode", "grow");
    ProGenContext.setProperty("progen.population.init-depth-interval", "3,5");
    ProGenContext.setProperty("progen.experiment.file", "test.progen.kernel.evolution.selector.Selector.txt");

    this.select(2);
    this.select(5);
    this.select(10);
  }

  private void select(int howMany) {
    Population population;
    population = new Population(howMany * 2);
    for (Individual individual : population.getIndividuals()) {
      individual.calculate(UserProgram.getUserProgram());
    }

    List<Individual> selection = selector.select(population, howMany);

    assertTrue(selection.size() == howMany);
    for (int i = 0; i < howMany; i++) {
      assertTrue(population.getIndividuals().contains(selection.get(i)));
    }
  }

  @Test@Ignore
  public void testMakeSelector() {
    Map<String, String> params = ProGenContext.getParameters("default.params");
    Selector roulette = Selector.makeSelector("Roulette", params);
    assertTrue(roulette instanceof Roulette);
  }

}
