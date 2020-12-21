import agh.cs.lab.AnimalSortingElement;
import agh.cs.lab.Config;
import agh.cs.lab.SimulationEngine;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

import static java.lang.System.out;

public class IntegrationAllClasses {

	@Test
	public void t1() throws IOException, ParseException {
		SimulationEngine simulationEngine = new SimulationEngine(new Config());
		simulationEngine.placeAnimals();
		Iterator<AnimalSortingElement> it = simulationEngine.getMap().animalsSorted.iterator();
		while(it.hasNext()) {
			out.println(it.next());
		}


	}

}
