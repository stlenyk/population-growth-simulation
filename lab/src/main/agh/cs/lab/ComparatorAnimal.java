package agh.cs.lab;

import java.util.Comparator;

public class ComparatorAnimal implements Comparator<Animal> {

	@Override
	public int compare(Animal o1, Animal o2) {
		Vector2d v1 = o1.getPosition();
		Vector2d v2 = o2.getPosition();
		if (v1.equals(v2)) {
			if (o1.getEnergy() != o2.getEnergy()) {
				return o2.getEnergy() - o1.getEnergy();
			} else {
				return o1.getID() - o2.getID();
			}
		} else if (v1.x == v2.x) {
			return v1.y - v2.y;
		}
		return v1.x - v2.x;
	}

}
