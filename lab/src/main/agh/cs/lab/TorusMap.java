package agh.cs.lab;

import java.util.*;

public class TorusMap implements IObserver {

	public final int width, height; //TODO czy musi być private skoro jest już final?
	public final double jungleRatio;
	public final Vector2d jungleLowerLeft, jungleUpperRight;
	public int currID = 0;
	public SortedSet<AnimalSortingElement> animals;
	public HashSet<Vector2d> plants;
	public HashSetGetRandom emptyJungle, emptySavanna, depopulatedAreas; // empty - no animals or plants; depopulated - no animals
	public HashMap<Vector2d, Integer> occupiedPositions; // number of animals on each position

	public TorusMap(int width, int height, double jungleRatio) { // TODO uwaga - tylko emptyJungle ma osobne, swoje obiekty Vector2d, natomiast reszta struktur ma te same - czy będzie to rodzić problemy?
		this.width = width;
		this.height = height;
		this.jungleRatio = jungleRatio;
		jungleLowerLeft = new Vector2d(this, width/2 - (int)(jungleRatio*width*0.5), height/2 - (int)(jungleRatio*height*0.5));
		jungleUpperRight = new Vector2d(this, width/2 + (int)(jungleRatio*width*0.5), height/2 + (int)(jungleRatio*height*0.5));
		animals = new TreeSet<>();
		plants = new HashSet<>();
		emptyJungle = new HashSetGetRandom((int) (jungleRatio*jungleRatio*width*height)); // TODO zastanowić się jakie dokładnie powinny być te wymiary
		emptySavanna = new HashSetGetRandom(width*height);
		depopulatedAreas = new HashSetGetRandom(width*height);
		occupiedPositions = new HashMap<>();
		for(int i = jungleLowerLeft.x; i < jungleUpperRight.x; i++) {
			for(int j = jungleLowerLeft.y; j < jungleUpperRight.y; j++) {
				emptyJungle.add(new Vector2d(this, i, j));
			}
		}
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				Vector2d position = new Vector2d(this, i, j);
				occupiedPositions.put(position, 0);
				depopulatedAreas.add(position);
				if(!emptyJungle.contains(position)) {
					emptySavanna.add(position);
				}
			}
		}
		//TODO sprawdzić czy dobre pola są dodawane do emptyJgl/Sav - m.in. czy jgl jest na środku mapy
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

//	public ArrayList<Object> objectAt(Vector2d position) {
//		ArrayList<Object> objectsAtposition = new ArrayList<>();
//		for(Animal i: animals) {
//			if(i.getPosition() == position) objectsAtposition.add(i);
//		}
//		for(Plant i: plants) {
//			if(i.getPosition() == position) objectsAtposition.add(i);
//		}
//		if (objectsAtposition.size() == 0) return null;
//		else return objectsAtposition;
//	}

	public Vector2d getRandomDepopulated() {
		return depopulatedAreas.getRandom();
	}

	public int genID() {
		return currID++;
	}

	public SortedSet getAnimals() {
		return animals;
	}

	public HashSet<Vector2d> getPlants() {
		return plants;
	}

	public HashSetGetRandom getDepopulatedAreas() {
		return depopulatedAreas;
	}

	@Override
	public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
		positionFreed(oldPosition, animal);
		positionTaken(newPosition, animal);
	}

	@Override
	public void positionFreed(Vector2d position, Animal animal) {
		animals.remove(new AnimalSortingElement(position, animal.getEnergy(), animal));
		int noOfAnimals = occupiedPositions.get(position);
		occupiedPositions.put(position, --noOfAnimals);
		if(noOfAnimals == 0) {
			if(position.follows(jungleLowerLeft) && position.precedes(jungleUpperRight)) {
				emptyJungle.add(position);
			}
			else {
				emptySavanna.add(position);
			}
			depopulatedAreas.add(position);
		}
	}

	public void plantEaten(Vector2d position) {
		plants.remove(position);
	}

	@Override
	public void positionTaken(Vector2d position, Animal animal) {
		animals.add(new AnimalSortingElement(position, animal.getEnergy(), animal));
		depopulatedAreas.remove(position);
		emptyJungle.remove(position);
		emptySavanna.remove(position);
		int NoOfAnimals = occupiedPositions.get(position) ;
		occupiedPositions.put(position, ++NoOfAnimals);
	}

	public void energyChanged(int oldEnergy, int newEnergy, Animal animal) {
		animals.remove(new AnimalSortingElement(animal.getPosition(), oldEnergy, animal));
		animals.add(new AnimalSortingElement(animal.getPosition(), newEnergy, animal));
	}


}
