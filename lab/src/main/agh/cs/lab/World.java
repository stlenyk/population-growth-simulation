package agh.cs.lab;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

import static java.lang.System.out;

public class World {

	public static void main(String[] args) throws IOException, ParseException {

		Random rand = new Random();
////		out.println(rand.nextInt(0));
//		out.println(hashMapGetRandom.contains(new Vector2d(2, 2)));
//		out.println(hashMapGetRandom.contains(new Vector2d(2, 3)));

//		SortedSet <Integer> sortedSet = new TreeSet();
//		for(int i=0; i<10; i++) sortedSet.add(rand.nextInt(10));
//		Iterator<Integer> it = sortedSet.iterator();
//		Integer animal;
//		while(it.hasNext()) {
//			animal = it.next();
//			out.println(animal);
//		}
//		out.println("\u21D1");
//		HashSetGetRandom hashSetGetRandom = new HashSetGetRandom(10);
//		TorusMap map = new TorusMap(1, 1, 2);
//		hashSetGetRandom.remove(new Vector2d(map,2, 3));

//		Config config = new Config();
//		out.println(config.height + " " + config.jungleRatio);
//		long t = System.nanoTime();
//		for(int i=0; i<10; i++) {
//			out.println(t);
//		}
		SimulationEngine simulationEngine = new SimulationEngine(new Config());
		simulationEngine.placeAnimals();
		for(int i=0; i<1000; i++) {
			simulationEngine.runSimulation();
		}
		Iterator<AnimalSortingElement> it = simulationEngine.getMap().animalsSorted.iterator();
		AnimalSortingElement animal;
		while(it.hasNext()) {
			animal = it.next();
//			out.println(it.next().position + "\t" + animal.energy);
			if(animal.energy <=0) out.println("en <= 0");
		}

	}

}










