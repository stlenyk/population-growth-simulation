package agh.cs.lab;

import java.util.*;

public class TorusMap implements IObserverPositions {

	public final int width, height;
	public final int moveEnergy, plantEnergy;
	public final double jungleRatio;
	public final Vector2d jungleLowerLeft, jungleUpperRight;
	private int currID = 0;
	public final SortedSet<AnimalSortingElement> animalsSorted;
	public final HashSet<Vector2d> plants, animals;
	public final HashSetGetRandom emptyJungle, emptySavanna, depopulatedAreas; // empty - no animalsSorted or plants; depopulated - no animalsSorted
	public final HashMap<Vector2d, Integer> occupiedPositions; // number of animalsSorted on each position

	public TorusMap(int width, int height, double jungleRatio, int moveEnergy, int plantEnergy) {
		this.width = width;
		this.height = height;
		this.jungleRatio = jungleRatio;
		this.moveEnergy = moveEnergy;
		this.plantEnergy = plantEnergy;
		jungleLowerLeft = new Vector2d(this, width/2 - (int)(jungleRatio*width*0.5), height/2 - (int)(jungleRatio*height*0.5)+1);
		jungleUpperRight = new Vector2d(this, width/2 + (int)(jungleRatio*width*0.5)-1, height/2 + (int)(jungleRatio*height*0.5));
		animalsSorted = new TreeSet<>();
		plants = new HashSet<>();
		animals = new HashSet<>();
		emptyJungle = new HashSetGetRandom((width*height));
		emptySavanna = new HashSetGetRandom(width*height);
		depopulatedAreas = new HashSetGetRandom(width*height);
		occupiedPositions = new HashMap<>();

		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				Vector2d position = new Vector2d(this, j, i);
				occupiedPositions.put(position, 0);
				depopulatedAreas.add(position);
				if(isJungle(position)) {
					emptyJungle.add(position);
				}
				else {
					emptySavanna.add(position);
				}
			}
		}
	}

	public boolean isJungle(Vector2d position) {
		return position.follows(jungleLowerLeft) && position.precedes(jungleUpperRight);
	}

	public void generatePlants() {
		if(emptyJungle.size() > 0) {
			Vector2d plantJungle = emptyJungle.popRandom();
			plants.add(plantJungle);
		}
		if(emptySavanna.size() > 0) {
			Vector2d plantSavanna = emptySavanna.popRandom();
			plants.add(plantSavanna);
		}
	}

	public boolean isOccupied(Vector2d position) {
		return !depopulatedAreas.contains(position);
	}

	public void place(Animal animal) {
		animal.addObserver(this);
		positionTaken(animal.getPosition(), animal);
	}

	public Vector2d getRandomDepopulated() {
		return depopulatedAreas.getRandom();
	}

	public int genID() {
		return currID++;
	}

	public SortedSet<AnimalSortingElement> getAnimalsSorted() {
		return animalsSorted;
	}

	public HashSet<Vector2d> getPlants() {
		return plants;
	}

	@Override
	public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
		positionFreed(oldPosition, animal);
		positionTaken(newPosition, animal);
	}

	@Override
	public void positionFreed(Vector2d position, Animal animal) {
		animalsSorted.remove(new AnimalSortingElement(position, animal.getEnergy(), animal));
		int noOfAnimals = occupiedPositions.get(position);
		occupiedPositions.put(position, --noOfAnimals);
		if(noOfAnimals == 0) {
			if(isJungle(position)) {
				emptyJungle.add(position);
			}
			else {
				emptySavanna.add(position);
			}
			depopulatedAreas.add(position);
			animals.remove(position);
		}
	}

	public void plantEaten(Vector2d position) {
		plants.remove(position);
	}

	@Override
	public void positionTaken(Vector2d position, Animal animal) {
		animals.add(position);
		animalsSorted.add(new AnimalSortingElement(position, animal.getEnergy(), animal));
		depopulatedAreas.remove(position);
		emptyJungle.remove(position);
		emptySavanna.remove(position);
		int NoOfAnimals = occupiedPositions.get(position);
		occupiedPositions.put(position, ++NoOfAnimals);
	}

	public void energyChanged(int oldEnergy, int newEnergy, Animal animal) {
		animalsSorted.remove(new AnimalSortingElement(animal.getPosition(), oldEnergy, animal));
		animalsSorted.add(new AnimalSortingElement(animal.getPosition(), newEnergy, animal));
	}


}
