package agh.cs.lab;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {

//	private int width, height;
//	private ArrayList<Animal> animals = new ArrayList<>();
//	private Vector2d lowerLeft = new Vector2d(0, 0), upperRight;
//	private MapVisualizer visualize;


	public RectangularMap(int width, int height) {
		this.width = width;
		this.height = height;
		visualize = new MapVisualizer(this);
	}

	public Object objectAt(Vector2d position) {
		for(Animal i: animals) {
			if(i.getPosition().equals(position))
				return i;
		}
		return null;
	}

	public Vector2d getLowerLeft() {
		return new Vector2d(0, 0);
	}

	public Vector2d getUpperRight() {
		return new Vector2d(width, height);
	}
}