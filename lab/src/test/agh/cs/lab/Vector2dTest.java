package agh.cs.lab;

import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2dTest {

	Vector2d V1 = new Vector2d(1, 1);
	Vector2d V2 = new Vector2d(1, 2);
	Vector2d V3 = new Vector2d(2, 1);
	Vector2d V4 = new Vector2d(2, 2);

	@Test
	public void toStringTest() {
		assertEquals("(1,1)", V1.toString());
	}

	@Test
	public void precedesTest() {
		assertTrue(V1.precedes(V4));
		assertTrue(V1.precedes(V1));
		assertTrue(V1.precedes(V2));
		assertFalse(V3.precedes(V2));
		assertFalse(V4.precedes(V2));
	}

	@Test
	public void followsTest() {
		assertTrue(V4.follows(V1));
		assertTrue(V4.follows(V4));
		assertTrue(V4.follows(V2));
		assertFalse(V1.follows(V2));
		assertFalse(V2.follows(V3));
	}

	@Test
	public void upperRight() {
		assertEquals(V1.upperRight(V1), V1);
		assertEquals(V1.upperRight(V2), V2);
		assertEquals(V1.upperRight(V3), V3);
		assertEquals(V2.upperRight(V3), V4);
	}

	@Test
	public void lowerLeftTest() {
		assertEquals(V1.lowerLeft(V1), V1);
		assertEquals(V2.lowerLeft(V1), V1);
		assertEquals(V3.lowerLeft(V2), V1);
		assertEquals(V1.lowerLeft(V4), V1);
	}

	@Test
	public void addTest() {
		Vector2d V12 = new Vector2d(2, 3);
		Vector2d V23 = new Vector2d(3, 3);
		assertEquals(V1.add(V2), V12);
		assertEquals(V2.add(V3), V23);
	}

	@Test
	public void subtractTest() {
		Vector2d V12 = new Vector2d(0, -1);
		Vector2d V23 = new Vector2d(-1, 1);
		assertEquals(V1.subtract(V2), V12);
		assertEquals(V2.subtract(V3), V23);
	}

	@Test
	public void equalsTest() {
		Vector2d V1copy = new Vector2d(1, 1);
		assertTrue(V1.equals(V1));
		assertTrue(V1.equals(V1copy));
		assertFalse(V1.equals(V2));
		assertFalse(V1.equals(123));
		assertFalse(V1.equals("abc"));

	}

	@Test
	public void oppositeTest() {
		Vector2d V1opp = new Vector2d(-1, -1);
		Vector2d V5 = new Vector2d(2, -1);
		Vector2d V5opp = new Vector2d(-2, 1);
		assertEquals(V1.opposite(), V1opp);
		assertEquals(V5.opposite(), V5opp);
	}

}