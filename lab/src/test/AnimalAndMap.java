import agh.cs.lab.*;
import org.junit.Test;

import java.util.Iterator;

import static java.lang.System.out;

import static org.junit.Assert.*;

public class AnimalAndMap {

	@Test
	public void t1() {
//		TorusMap map = new TorusMap(20, 10, 0.5);
//		Visualizer visualizer = new Visualizer(map);
//		Animal a1 = new Animal(map, new Vector2d(map,3, 3), 10);
//		Animal a2 = new Animal(map, new Vector2d(map,5, 4), 15);
//		map.place(a1);
//		map.place(a2);
//		out.println();
//		out.println("jgl " + map.emptyJungle.size());
//		out.println("sav " + map.emptySavanna.size());
//		out.println("dep " + map.depopulatedAreas.size());
//		out.println(map.jungleLowerLeft + " " + map.jungleUpperRight);
//		map.generatePlants();
//		out.println(map.plants.size());
//		visualizer.draw();
//		out.println(map.depopulatedAreas.contains(a1.getPosition()));
//		out.println();

		SimulationEngine simulationEngine = new SimulationEngine(80, 30, 0.3, 10, 1, 10, 0);
		Visualizer visualizer = new Visualizer(simulationEngine.getMap());
		simulationEngine.placeAnimals();
		visualizer.draw();
		for(int i=0; i<5; i++) {
			for(int j=0; j<50; j++) {
				simulationEngine.simulate();
			}
			out.println("emptyJungle: " + simulationEngine.getMap().emptyJungle.size());
			visualizer.draw();
			out.println();
		}
//		TorusMap map = simulationEngine.getMap();
//		for(int i=0; i<100; i++) {
//			map.generatePlants();
//			out.println(map.emptyJungle.size());
//		}
//		int n = 100;
//		TorusMap map = new TorusMap(2,1, 3);
//		HashSetGetRandom hashSetGetRandom = new HashSetGetRandom(n);
//		for(int i=0; i<n; i++) {
//			hashSetGetRandom.add(new Vector2d(map, i, i));
//		}
//		for(int i=0; i<n; i++) {
//			hashSetGetRandom.popRandom();
//			out.println(hashSetGetRandom.size());
//		}




	}






}






