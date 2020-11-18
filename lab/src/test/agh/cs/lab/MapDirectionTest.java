package agh.cs.lab;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapDirectionTest {

	MapDirection n = MapDirection.NORTH;
	MapDirection e = MapDirection.EAST;
	MapDirection s = MapDirection.SOUTH;
	MapDirection w = MapDirection.WEST;

	@Test
	public void testNext() {
		assertEquals(MapDirection.NORTH, w.next());
		assertEquals(MapDirection.EAST, n.next());
		assertEquals(MapDirection.SOUTH, e.next());
		assertEquals(MapDirection.WEST, s.next());
	}

	@Test
	public void testPrevious() {
		assertEquals(MapDirection.NORTH, e.previous());
		assertEquals(MapDirection.EAST, s.previous());
		assertEquals(MapDirection.SOUTH, w.previous());
		assertEquals(MapDirection.WEST, n.previous());
	}



}
