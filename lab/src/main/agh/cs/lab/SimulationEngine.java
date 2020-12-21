package agh.cs.lab;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.SortedSet;

public class SimulationEngine {

	private final TorusMap map;
	public final int startEnergy, moveEnergy, plantEnergy, noOfAnimals;
	private long turnCount=0, deathCount=0;

	public SimulationEngine(Config config) {
		this.startEnergy = config.startEnergy;
		this.moveEnergy = config.moveEnergy;
		this.plantEnergy = config.plantEnergy;
		this.noOfAnimals = config.startAnimals;
		map = new TorusMap(config.width, config.height, config.jungleRatio, config.moveEnergy, config.plantEnergy);
	}

	public void runSimulation() {
		map.generatePlants();
		move();
		breed();
		eat();
		corpseDisposal();
		turnCount++;
	}

	public void placeAnimals() {
		for(int i=0; i<noOfAnimals; i++) {
			map.place(Animal.startingAnimal(map, startEnergy));
		}
	}

	private void move() {
		SortedSet<AnimalSortingElement> animals = map.getAnimalsSorted();
		ArrayList<Animal> animalsToMove = new ArrayList<>();
		Iterator<AnimalSortingElement> it = animals.iterator();
		while(it.hasNext()) {
			animalsToMove.add(it.next().animal);
		}
		for(Animal animal: animalsToMove) {
			animal.move();
		}
	}

	private void breed() {
		SortedSet<AnimalSortingElement> animals = map.getAnimalsSorted();
		if(animals.isEmpty()) return;
		Animal parent1, parent2;
		ArrayList<Animal> childrenToPlace = new ArrayList<>();
		ArrayList<Animal> reproducingParents = new ArrayList<>();
		Iterator<AnimalSortingElement> it = animals.iterator();
		parent1 = it.next().animal;
		boolean areaBred = false;
		while(it.hasNext()) {
			parent2 = it.next().animal;
			if(!parent1.getPosition().equals((parent2.getPosition()))) {
				areaBred = false;
			}
			else if(!areaBred && parent2.getEnergy() > (double) startEnergy*0.5) {
				areaBred = true;
				Animal child = Animal.breed(parent1, parent2);
				childrenToPlace.add(child);
				reproducingParents.add(parent1);
				reproducingParents.add(parent2);
			}
			parent1 = parent2;
		}
		for(Animal child: childrenToPlace) {
			map.place(child);
		}
		for(Animal parent: reproducingParents) {
			parent.reproduced();
		}

	}

	private void eat() {
		SortedSet<AnimalSortingElement> animals = map.getAnimalsSorted();
		if(animals.isEmpty()) return;
		HashSet<Vector2d> plants = map.getPlants();
		Iterator<AnimalSortingElement> it = animals.iterator();
		ArrayList <ArrayList<Animal>> animalsToFeed = new ArrayList<>();
		ArrayList<Animal> currPlant = new ArrayList<>();
		Animal animal;
		do {
			animal = it.next().animal;
			if(!plants.contains(animal.getPosition())) continue;
			if (currPlant.size() != 0) {
				if (!animal.getPosition().equals(currPlant.get(0).getPosition())) {
					animalsToFeed.add(currPlant);
					currPlant = new ArrayList<>();
				}
			}
			currPlant.add(animal);
		} while(it.hasNext());
		animalsToFeed.add(currPlant);
		for(ArrayList<Animal> currPlant1: animalsToFeed) {
			for(Animal animal1: currPlant1) {
				animal1.eat();
			}
			if(currPlant1.size() > 0) map.plantEaten(currPlant1.get(0).getPosition());
		}

	}

	private void corpseDisposal() {
		SortedSet<AnimalSortingElement> animals = map.getAnimalsSorted();
		Iterator<AnimalSortingElement> it = animals.iterator();
		ArrayList<Animal> corpsesToDispose = new ArrayList<>();
		Animal animal;
		while(it.hasNext()) {
			animal = it.next().animal;
			if(animal.getEnergy() <= 0) corpsesToDispose.add(animal);
		}
		for(Animal animal1: corpsesToDispose) {
			animal1.die();
			deathCount++;
		}

	}

	public TorusMap getMap() {
		return map;
	}

	public long getTurnCount() {
		return turnCount;
	}

	public long getDeathCount() {
		return deathCount;
	}



}










