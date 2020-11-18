package agh.cs.lab;

public class Grass implements IMapElement {

	private Vector2d position;

	Grass(Vector2d position) {
		this.position = position;
	}

	public Vector2d getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "*";
	}

}
