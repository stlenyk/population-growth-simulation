package agh.cs.lab;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

	SortedSet<IMapElement> objectsX = new TreeSet<>(new ComparatorX());
	SortedSet<IMapElement> objectsY = new TreeSet<>(new ComparatorY());

	@Override
	public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
		if(oldPosition.x != newPosition.x) {
			objectsX.remove(animal);
			objectsX.add(animal);
		}
		if(oldPosition.y != newPosition.y) {
			objectsY.remove(animal);
			objectsY.add(animal);
		}
	}

	void place(IMapElement element) {
		if(element instanceof Animal)
			((Animal) element).addObserver(this);
		objectsX.add(element);
		objectsY.add(element);
	}

	public Vector2d getBoundaryLowerLeft() {
		return new Vector2d(objectsX.first().getPosition().x, objectsY.first().getPosition().y);
	}

	public Vector2d getBoundaryUpperRight() {
		return new Vector2d(objectsX.last().getPosition().x, objectsY.last().getPosition().y);
	}

}
