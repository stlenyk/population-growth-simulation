package agh.cs.lab;

public enum MapDirection {
	NORTH,
	SOUTH,
	WEST,
	EAST;

	public String toSring() {
		switch(this) {
			case NORTH:
				return "Północ";
			case EAST:
				return "Wschód";
			case SOUTH:
				 return "Południe";
			case WEST:
				return "Zachód";
			default:
				throw new IllegalStateException("Unexpected value: " + this);
		}
	}

	public MapDirection next() {
		switch(this) {
			case NORTH:
				return EAST;
			case EAST:
				return SOUTH;
			case SOUTH:
				return WEST;
			case WEST:
				return NORTH;
			default:
				throw new IllegalStateException("Unexpected value: " + this);
		}
	}

	public MapDirection previous() {
		switch(this) {
			case NORTH:
				return WEST;
			case EAST:
				return NORTH;
			case SOUTH:
				return EAST;
			case WEST:
				return SOUTH;
			default:
				throw new IllegalStateException("Unexpected value: " + this);
		}
	}

	public Vector2d toUnitVector() {
		Vector2d unitVector;
		switch(this) {
			case NORTH:
				unitVector = new Vector2d(0, 1);
				break;
			case EAST:
				unitVector = new Vector2d(1, 0);
				break;
			case SOUTH:
				unitVector = new Vector2d (0, -1);
				break;
			case WEST:
				unitVector = new Vector2d(-1, 0);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + this);
		}
		return unitVector;
	}

}
