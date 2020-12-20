package agh.cs.lab;

import java.util.*;

import static java.lang.System.out;

public class World {

	public static void main(String[] args) {

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
		out.println("\u21D1");
		HashSetGetRandom hashSetGetRandom = new HashSetGetRandom(10);
		TorusMap map = new TorusMap(1, 1, 2);
		hashSetGetRandom.remove(new Vector2d(map,2, 3));


	}

}










