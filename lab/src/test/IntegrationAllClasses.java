import agh.cs.lab.SimulationEngine;
import agh.cs.lab.view.VisualizerConsole;
import org.junit.Test;

import static java.lang.System.out;

public class IntegrationAllClasses {

	@Test
	public void t1() {

		SimulationEngine simulationEngine = new SimulationEngine(30, 15, 0.3, 10, 1, 10, 10);
		VisualizerConsole visualizerConsole = new VisualizerConsole(simulationEngine.getMap());
		simulationEngine.placeAnimals();
//		visualizerConsole.draw();
		for(int i=0; i<1; i++) {
			for(int j=0; j<1; j++) {
				simulationEngine.runSimulation();
			}
			out.println("emptyJungle: " + simulationEngine.getMap().emptyJungle.size());
			visualizerConsole.draw();
			out.println();
			out.println("co");
		}



	}

}
