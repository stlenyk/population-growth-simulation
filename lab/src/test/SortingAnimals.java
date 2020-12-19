import agh.cs.lab.Animal;
import agh.cs.lab.ComparatorAnimal;
import agh.cs.lab.TorusMap;
import agh.cs.lab.Vector2d;
import org.junit.Test;

import java.util.*;

import static java.lang.System.out;

public class SortingAnimals {

	@Test
	public void t1() {
		TorusMap map = new TorusMap(100, 100, 0.01);
		ArrayList<Animal> animals = new ArrayList<>();
		animals.add(new Animal(map, new Vector2d(map, 5, 6), 9));
		for(int i=0; i<5; i++) {
			animals.add(new Animal(map, new Vector2d(map, 2, 3), i));
			animals.add(new Animal(map, new Vector2d(map, 1, 3), i));
		}
		animals.add(new Animal(map, new Vector2d(map, 2, 3), 3));
		animals.add(new Animal(map, new Vector2d(map, 4, 5), 1));
		SortedSet<Animal> animalSortedSet = new TreeSet<>(new ComparatorAnimal());
		Collections.shuffle(animals);
		for(Animal i: animals) {
			animalSortedSet.add(i);
		}
		Iterator<Animal> it = animalSortedSet.iterator();
		Animal animal;
		while(it.hasNext()) {
			animal = it.next();
			out.println(animal.getPosition() + " " + animal.getEnergy() + " " + animal.getID());
		}
//		for(Animal i: animals) {
//			out.print(i.getID() + " ");
//		}
//		out.println();
//		animals.sort(new ComparatorAnimal());
//		for(Animal animal: animals) {
//			out.println(animal.getPosition() + " " + animal.getEnergy() + " " + animal.getID());
//		}
	}

}
