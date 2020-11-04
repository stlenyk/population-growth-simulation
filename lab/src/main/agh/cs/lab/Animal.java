package agh.cs.lab;

public class Animal {

	private MapDirection exposure = MapDirection.NORTH;
	private Vector2d position = new Vector2d(2, 2);
	private IWorldMap map;

	Animal(IWorldMap map) {
		this.map = map;
	}

	Animal(IWorldMap map, Vector2d initialPosition) {
		this.map = map;
		this.position = initialPosition;
	}

	@Override
	public String toString() {
		switch (exposure) {
			case NORTH:
				return "N";
			case EAST:
				return "E";
			case SOUTH:
				return "S";
			case WEST:
				return "W";
			default:
				return null;
		}
	}

	void move(MoveDirection direction) {
		Vector2d newPosition;
		switch (direction) {
			case LEFT:
				this.exposure = this.exposure.previous();
				break;
			case RIGHT:
				this.exposure = this.exposure.next();
				break;
			case FORWARD:
				newPosition = this.position.add(this.exposure.toUnitVector());
				if(map.canMoveTo(newPosition))
					this.position = newPosition;
				break;
			case BACKWARD:
				newPosition = this.position.subtract(this.exposure.toUnitVector());
				if(map.canMoveTo(newPosition))
					this.position = newPosition;
				break;
		}
	}

	MapDirection getExposure() {
		return exposure;
	}

	Vector2d getPosition() {
		return position;
	}





}