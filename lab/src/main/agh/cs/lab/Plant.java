package agh.cs.lab;

public class Plant { //TODO wyalić plant - nie jest obecnie używane

	private Vector2d position;

	Plant(Vector2d position) {
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
