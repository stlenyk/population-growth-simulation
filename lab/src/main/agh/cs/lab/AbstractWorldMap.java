package agh.cs.lab;

import java.util.ArrayList;

abstract class AbstractWorldMap {

	protected int width, height;
	protected ArrayList<Animal> animals = new ArrayList<>();
	protected MapVisualizer visualize;

	@Override
	public String toString() {
		return visualize.draw(getLowerLeft(), getUpperRight());
	}

	public boolean place(Animal animal) {
		if(!canMoveTo(animal.getPosition()))
			return false;
		animals.add(animal);
		return true;
	}

	public boolean canMoveTo(Vector2d position) {
		return position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(width, height)) && !(objectAt(position) instanceof Animal);
	}

	public void run(MoveDirection[] directions) {
		for(int i=0; i<directions.length; i++) {
			animals.get(i % animals.size()).move(directions[i]);
		}
	}

	public boolean isOccupied(Vector2d position) {
		if(objectAt(position) == null)
			return false;
		else return true;
	}

	protected abstract Object objectAt(Vector2d position);

	protected abstract Vector2d getLowerLeft();

	protected abstract Vector2d getUpperRight();

}
