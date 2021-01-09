package agh.cs.lab;

import java.util.Random;

public enum MapDirection {
	NORTH,
	NORTHEAST,
	EAST,
	SOUTHEAST,
	SOUTH,
	SOUTHWEST,
	WEST,
	NORTHWEST;

	@Override
	public String toString() {
		String s = " ";
		switch (this) {
			case NORTH:
				s = "\u21D1";
				break;
			case NORTHEAST:
				s = "\u21D7";
				break;
			case EAST:
				s = "\u21D2";
				break;
			case SOUTHEAST:
				s = "\u21D8";
				break;
			case SOUTH:
				s = "\u21D3";
				break;
			case SOUTHWEST:
				s = "\u21D9";
				break;
			case WEST:
				s = "\u21D0";
				break;
			case NORTHWEST:
				s = "\u21D6";
				break;
		}
		return s;
	}

	public MapDirection rotate(int rotation) {
		int n = MapDirection.values().length;
		return MapDirection.values()[(this.ordinal() + rotation) % n];
	}

	public Vector2d toUnitVector(TorusMap map) {
		Vector2d unitVector;
		switch(this) {
			case NORTH:
				unitVector = new Vector2d(map, 0, 1);	// nowy wektor co wywołanie
				break;
			case NORTHEAST:
				unitVector = new Vector2d(map, 1,1);
				break;
			case EAST:
				unitVector = new Vector2d(map, 1, 0);
				break;
			case SOUTHEAST:
				unitVector = new Vector2d(map, 1, -1);
				break;
			case SOUTH:
				unitVector = new Vector2d (map, 0, -1);
				break;
			case SOUTHWEST:
				unitVector = new Vector2d(map, -1, -1);
				break;
			case WEST:
				unitVector = new Vector2d(map, -1, 0);
				break;
			case NORTHWEST:
				unitVector = new Vector2d(map, -1, 1);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + this);
		}
		return unitVector;
	}

	public MapDirection next() {
		int n = MapDirection.values().length;
		return MapDirection.values()[(this.ordinal() + 1) % n];
	}

	public static MapDirection getRandom() {
		Random rand = new Random();	// nowy obiekt co wywołanie
		return MapDirection.values()[rand.nextInt(8)];
	}

}
