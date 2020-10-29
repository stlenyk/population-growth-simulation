package agh.cs.lab;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {

	public int width, height;	// to nie może być public
	public ArrayList<Animal> animals = new ArrayList<>();

	RectangularMap(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public String toString() {
		MapVisualizer drawer = new MapVisualizer(this);	// visualizer i 2 wektory co metodę
		Vector2d lowerLeft = new Vector2d(0, 0);
		Vector2d upperRight = new Vector2d(width, height);	// width - 1
		return drawer.draw(lowerLeft, upperRight);
	}

	public boolean canMoveTo(Vector2d position) {
		int x = position.x, y = position.y;
		return x>0 && x<=this.width && y>0 && y<=this.height && !isOccupied(position);	// nie lepiej na poziomie wektorów
																						// < , nie <=
	}

	public boolean place(Animal animal) {
		if(isOccupied(animal.getPosition()))	// czy na pewno isOccupied?
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
		if(isOccupied(position))		// czy to sprawdzenie warunku jest potrzebne?
			for(Animal i: animals) {	// uderzająco podobna do poprzedniej metody
				if(i.getPosition().equals(position))
					return i;
			}
		return null;
	}
}