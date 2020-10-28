package agh.cs.lab;

public class Animal {

	private MapDirection exposure = MapDirection.NORTH;
	private Vector2d position = new Vector2d(2, 2);
	private IWorldMap map;

	Animal(){}

	Animal(IWorldMap map) {
		this.map = map;
	}

	Animal(IWorldMap map, Vector2d initialPosition) {
		this.map = map;
		this.position = initialPosition;
	}

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
				return "";
		}
	}

	void move(MoveDirection direction) {
		switch (direction) {
			case LEFT:
				this.exposure = this.exposure.previous();
				break;
			case RIGHT:
				this.exposure = this.exposure.next();
				break;
			case FORWARD:
				if(map.canMoveTo(this.position.add(this.exposure.toUnitVector())))
					this.position = this.position.add(exposure.toUnitVector());
				break;
			case BACKWARD:
				if(map.canMoveTo(this.position.subtract(this.exposure.toUnitVector())))
					this.position = this.position.subtract(this.exposure.toUnitVector());
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
