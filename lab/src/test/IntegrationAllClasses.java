import agh.cs.lab.SimulationEngine;
import agh.cs.lab.Visualizer;
import org.junit.Test;
import static java.lang.System.out;

public class IntegrationAllClasses {

	@Test
	public void t1() {
		SimulationEngine simulationEngine = new SimulationEngine(10, 15, 0.1, 10, 1, 10, 5);
		simulationEngine.placeAnimals();
		Visualizer visualizer = new Visualizer(simulationEngine.getMap());
		for(int i=0; i<10; i++) {
			simulationEngine.simulate();
			visualizer.draw();
			out.println();
		}


	}

}
