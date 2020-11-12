package agh.cs.lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

abstract class AbstractWorldMap implements IWorldMap {

	protected int width, height;	// ile wynosi szerokość GrassField?
//	protected Vector2d lowerLeft = new Vector2d (0, 0), upperRight;
	protected ArrayList<Animal> animals = new ArrayList<>();	// te trzy pola mogą być finalne
	protected HashMap<Vector2d, Animal> animalsHashed = new HashMap<>();
	private MapVisualizer visualize;	// odradzam używanie czasownika na nazwę zmiennej

	public AbstractWorldMap() {
		this.visualize = new MapVisualizer(this);
//		upperRight = new Vector2d(this.width, this.height);
	}

	@Override
	public String toString() {
		return visualize.draw(getLowerLeft(), getUpperRight());
	}

	public boolean place(Animal animal) {
		if(!canMoveTo(animal.getPosition()))
			throw new IllegalArgumentException(animal.getPosition() + " - can't place the animal here");
		animals.add(animal);
		animalsHashed.put(animal.getPosition(), animal);
		return true;
	}

	public boolean canMoveTo(Vector2d position) {
		return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height)) && !(objectAt(position) instanceof Animal);	// GrassField ma być nieograniczona; a gdyby nie, to czy trzeba tworzyć te wektory co wywołanie?
	}

	public void run(MoveDirection[] directions) {
		for(int i=0; i<directions.length; i++) {
			Animal animal = animals.get(i % animals.size());
			animalsHashed.remove(animal.getPosition());
			animal.move(directions[i]);
			animalsHashed.put(animal.getPosition(), animal);
		}
	}

	public Object objectAt(Vector2d position) {
		if(animalsHashed.containsKey(position))
			return animalsHashed.get(position);
		return null;
	}

	public boolean isOccupied(Vector2d position) {
		return !(objectAt(position) == null);
	}

	protected abstract Vector2d getLowerLeft();

	protected abstract Vector2d getUpperRight();

}
