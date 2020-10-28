package agh.cs.lab;

import org.junit.Test;

import static org.junit.Assert.*;

public class lab4Test {


	@Test
	public void t1() {
		String[] args = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
		MoveDirection[] directions = new OptionsParser().parse(args);
		IWorldMap map = new RectangularMap(4, 4);
		Animal animal = new Animal(map);
		map.place(animal);
		map.run(directions);
		assertEquals(animal.getExposure(), MapDirection.NORTH);
		assertEquals(animal.getPosition(), new Vector2d(2, 4));
	}

	public void t2() {
		String[] args = {"r", "f", "r", "f", "l", "f", "r"};
		MoveDirection[] directions = new OptionsParser().parse(args);
		IWorldMap map = new RectangularMap(10, 10);
		Animal a1 = new Animal(map);
		Animal a2 = new Animal(map, new Vector2d(2,4));
		map.place(a1);
		map.place(a2)
		map.run(directions);
		assertEquals(animal.getExposure(), MapDirection.NORTH);
		assertEquals(animal.getPosition(), new Vector2d(0, 4));
	}

}