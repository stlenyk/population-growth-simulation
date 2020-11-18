package agh.cs.lab;

import java.util.*;

public class Animal implements IMapElement {

	private MapDirection exposure = MapDirection.NORTH;
	private Vector2d position;
	private IWorldMap map;
	private final List<IPositionChangeObserver> observers;

	Animal(IWorldMap map) {
		this(map, new Vector2d(2, 2));
	}

	Animal(IWorldMap map, Vector2d initialPosition) {
		this.map = map;
		this.position = initialPosition;
		this.observers = new LinkedList<>();
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
		Vector2d newPosition, oldPosition = this.position;
		switch (direction) {
			case LEFT:
				this.exposure = this.exposure.previous();
				break;
			case RIGHT:
				this.exposure = this.exposure.next();
				break;
			case FORWARD:
				newPosition = this.position.add(this.exposure.toUnitVector());
				if(map.canMoveTo(newPosition)) {
					this.position = newPosition;
					positionChanged(oldPosition, newPosition);
				}
				break;
			case BACKWARD:
				newPosition = this.position.subtract(this.exposure.toUnitVector());
				if(map.canMoveTo(newPosition)) {
					this.position = newPosition;
					positionChanged(oldPosition, newPosition);
				}
				break;
		}
	}

	MapDirection getExposure() {
		return exposure;
	}

	public Vector2d getPosition() {
		return position;
	}

	void addObserver(IPositionChangeObserver observer) {
		observers.add(observer);
	}

	void removeObserver(IPositionChangeObserver observer) {
		observers.remove(observer);
	}

	private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
		observers.forEach(observer -> observer.positionChanged(oldPosition, newPosition, this));
	}


}