package agh.cs.lab;

public class Animal {

	private MapDirection exposure = MapDirection.NORTH;
	private Vector2d position = new Vector2d(2, 2);

	public String toString() {
		String s = String.valueOf("exposure: " + exposure.toSring() + "\t" + "position: " + position.toString());
		return s;
		// <=> return "exposure: " + exposure.toSring() + "\t" + "position: " + position.toString();
	}

	// wymiary planszy
	private int maxX = 4, maxY = 4, minX = 0, minY=0;	// lepsze by były wektory

	void move(MoveDirection direction) { // public
		Animal other = new Animal();  // przesunięcie zwierzęcia powoduje urodzenie się nowego?
		switch (direction) {
			case LEFT:
				this.exposure = this.exposure.previous();
				break;
			case RIGHT:
				this.exposure = this.exposure.next();
				break;
			case FORWARD:
				other.position = this.position.add(exposure.toUnitVector());
				if(other.position.x > maxX || other.position.y > maxY || other.position.x < minX || other.position.y < minY)
					break;
				this.position = this.position.add(exposure.toUnitVector());
				break;
			case BACKWARD:
				other.position = this.position.subtract(exposure.toUnitVector());
				if(other.position.x > maxX || other.position.y > maxY || other.position.x < minX || other.position.y < minY)
					break;
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
