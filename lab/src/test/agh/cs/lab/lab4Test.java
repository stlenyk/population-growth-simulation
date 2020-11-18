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
		assertEquals(MapDirection.NORTH, animal.getExposure());
		assertEquals(new Vector2d(2, 4), animal.getPosition());
	}

	@Test
	public void t2() {
		String[] args = {"f", "r", "f", "r", "f", "l", "f", "r"};
		MoveDirection[] directions = new OptionsParser().parse(args);
		IWorldMap map = new RectangularMap(10, 10);
		Animal a1 = new Animal(map);
		Animal a2 = new Animal(map, new Vector2d(2,4));
		map.place(a1);
		map.place(a2);
		map.run(directions);
		assertEquals(new Vector2d(2,3), a1.getPosition());
		assertEquals(MapDirection.SOUTH, a2.getExposure());
	}

	@Test
	public void t3() {
		String[] args = {"f", "l", "r", "f", "f", "b", "l", "r", "r", "b", "b", "b", "b"};
		IWorldMap map = new RectangularMap(20, 20);
		MoveDirection[] directions = new OptionsParser().parse(args);
		Animal a1 = new Animal(map, new Vector2d(5, 10));
		Animal a2 = new Animal(map, new Vector2d(15,10));
		map.place(a1);
		map.place(a2);
		map.run(directions);
		assertEquals(new Vector2d(4, 11), a1.getPosition());
		assertEquals(MapDirection.EAST, a1.getExposure());
		assertEquals(new Vector2d(15, 8), a2.getPosition());
		assertEquals(MapDirection.NORTH, a2.getExposure());
	}

	@Test
	public void t4() {
		String[] args = {"l", "f", "b", "r", "b", "b"};
		IWorldMap map = new RectangularMap(20,20);
		MoveDirection[] directions = new OptionsParser().parse(args);
		Animal a = new Animal(map, new Vector2d(15, 10));
		map.place(a);
		map.run(directions);
		assertEquals(new Vector2d(15, 8), a.getPosition());
	}

}