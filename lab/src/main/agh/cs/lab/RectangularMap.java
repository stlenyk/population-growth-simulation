package agh.cs.lab;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {

	private Vector2d lowerLeft;
	private Vector2d upperRight;

	public RectangularMap(int width, int height) {
		this.width = width;
		this.height = height;
		lowerLeft = new Vector2d(0, 0);
		upperRight = new Vector2d(width, height);
	}

	protected Vector2d getLowerLeft() {
		return lowerLeft;
	}

	protected Vector2d getUpperRight() {
		return upperRight;
	}
}