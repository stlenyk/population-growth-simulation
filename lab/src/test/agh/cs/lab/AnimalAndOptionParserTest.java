package agh.cs.lab;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalAndOptionParserTest {

	@Test
	public void parseTest() {
		String[] directionsString = {"f", "22as", "forward", "kei3", "b", "backward",  "aa", "t", "l", "left", "right", "r"};
		OptionsParser parser = new OptionsParser();
		MoveDirection[] directionsParsed = parser.parse(directionsString);
		MoveDirection[] directions = {
			MoveDirection.FORWARD, MoveDirection.FORWARD,
			MoveDirection.BACKWARD, MoveDirection.BACKWARD,
			MoveDirection.LEFT, MoveDirection.LEFT,
			MoveDirection.RIGHT, MoveDirection.RIGHT
		};
		assertEquals(directionsParsed, directions); // assertArrayEquals
	}


	public void moveTest(MoveDirection[] directions, MapDirection exposure, Vector2d position) {
		Animal zwierz = new Animal();	// polglish
		for(MoveDirection i: directions)
			zwierz.move(i);
		assertEquals(exposure, zwierz.getExposure());
		assertEquals(position, zwierz.getPosition());
	}

	@Test
	public void moveCreator() {

		MoveDirection[] d1 = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};
		MapDirection e1 = MapDirection.NORTH;
		Vector2d p1 =  new Vector2d(2, 4);

		MoveDirection[] d2 = {MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD};
		MapDirection e2 = MapDirection.NORTH;
		Vector2d p2 =  new Vector2d(2,0);

		MoveDirection[] d3 = {MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD};
		MapDirection e3 = MapDirection.WEST;
		Vector2d p3 =  new Vector2d(0, 2);

		MoveDirection[] d4 = {MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD};
		MapDirection e4 = MapDirection.WEST;
		Vector2d p4 =  new Vector2d(4, 2);

		MoveDirection[] d5 = {MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD};
		MapDirection e5 = MapDirection.EAST;
		Vector2d p5 =  new Vector2d(3, 1);

		MoveDirection[] d6 = {MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT};
		MapDirection e6 = MapDirection.SOUTH;
		Vector2d p6 =  new Vector2d(1, 3);

		moveTest(d1, e1, p1);
		moveTest(d2, e2, p2);
		moveTest(d3, e3, p3);
		moveTest(d4, e4, p4);
		moveTest(d5, e5, p5);
		moveTest(d6, e6, p6);

	}



}