package agh.cs.lab;

import org.junit.Test;

import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

public class lab5Test {

	@Test
	public void t1() {
		String[] args = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
		MoveDirection[] directions = new OptionsParser().parse(args);
		IWorldMap map = new GrassField(10);
		Animal animal = new Animal(map);
		map.place(animal);
		map.run(directions);
		assertEquals(MapDirection.NORTH, animal.getExposure());
		assertEquals(new Vector2d(2, 12), animal.getPosition());
	}

	@Test
	public void t2() {
		String[] args = {"f", "r", "f", "r", "f", "l", "f", "r"};
		MoveDirection[] directions = new OptionsParser().parse(args);
		IWorldMap map = new GrassField(0);
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
		IWorldMap map = new GrassField(0);
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
		String[] args = {"b", "b", "b", "b", "b", "b"};
		MoveDirection[] directions = new OptionsParser().parse(args);
		IWorldMap map = new GrassField(0);
		Animal animal = new Animal(map);
		map.place(animal);
		map.run(directions);
		assertEquals(MapDirection.NORTH, animal.getExposure());
		assertEquals(new Vector2d(2, 0), animal.getPosition());
	}

	public int countGrasses(IWorldMap map, int n) {
		int grassCount = 0;
		int range = (int) sqrt(n*10);
		for(int i=0; i<=range; i++) {
			for(int j=0; j<=range; j++) {
				if(map.objectAt(new Vector2d(i, j)) instanceof Grass)
					grassCount++;
			}
		}
		return grassCount;
	}

	@Test
	public void testGrasses() {
		IWorldMap map1 = new GrassField(0);
		IWorldMap map2 = new GrassField(10);
		IWorldMap map3 = new GrassField(100);
		assertEquals(0, countGrasses(map1, 0));
		assertEquals(10, countGrasses(map2, 10));
		assertEquals(100, countGrasses(map3, 100));
		for(int i=0; i<100; i++) {
			assertEquals(100, countGrasses(new GrassField(100), 100));
		}
	}

}
