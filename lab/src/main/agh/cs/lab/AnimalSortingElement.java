package agh.cs.lab;

public class AnimalSortingElement implements Comparable<AnimalSortingElement> {

	private final Vector2d position;
	private final int energy;
	public final Animal animal;

	public AnimalSortingElement(Vector2d position, int energy, Animal animal) {
		this.position = position;
		this.energy = energy;
		this.animal = animal;
	}

	@Override
	public int compareTo(AnimalSortingElement other) {
		Vector2d v1 = this.position;
		Vector2d v2 = other.position;
		int e1 = this.energy, e2 = other.energy;
		if (v1.equals(v2)) {
			if (e1 != e2) {
				return e2 - e1;
			} else {
				return this.animal.getID() - other.animal.getID();
			}
		} else if (v1.x == v2.x) {
			return v1.y - v2.y;
		}
		return v1.x - v2.x;
	}


}
