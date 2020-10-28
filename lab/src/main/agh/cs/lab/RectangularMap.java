package agh.cs.lab;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {

	public int width, height;
	public ArrayList<Animal> animals = new ArrayList<>();

	RectangularMap(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public String toString() {
		MapVisualizer drawer = new MapVisualizer(this);
		Vector2d lowerLeft = new Vector2d(0, 0);
		Vector2d upperRight = new Vector2d(width, height);
		return drawer.draw(lowerLeft, upperRight);
	}

	public boolean canMoveTo(Vector2d position) {
		int x = position.x, y = position.y;
		return x>0 && x<=this.width && y>0 && y<=this.height && !isOccupied(position);
	}

	public boolean place(Animal animal) {
		if(isOccupied(animal.getPosition()))
			return false;
		animals.add(animal);
		return true;
	}

	public void run(MoveDirection[] directions) {
		for(int i=0; i<directions.length; i++) {
			animals.get(i % animals.size()).move(directions[i]);
		}
	}

	public boolean isOccupied(Vector2d position) {
		for(Animal i: animals) {
			if(i.getPosition().equals(position))
				return true;
		}
		return false;
	}

	public Object objectAt(Vector2d position) {
		if(isOccupied(position))
			for(Animal i: animals) {
				if(i.getPosition().equals(position))
					return i;
			}
		return null;
	}
}