package agh.cs.lab;

import java.util.*;

public class Animal {

	private MapDirection exposure = MapDirection.NORTH;
	private Vector2d position;
	private final TorusMap map;
	private Genotype genotype;
	private int energy;
	private final int ID;
	private final List<IObserverPositions> observers;

	public Animal(TorusMap map, Vector2d initialPosition, int energy) {
		this.map = map;
		this.position = initialPosition;
		this.energy = energy;
		this.ID = map.genID();
		this.observers = new LinkedList<>();
		this.genotype = new Genotype();
	}

	public static Animal startingAnimal(TorusMap map, int energy) {
		Vector2d position = map.getRandomDepopulated();
		return new Animal(map, position, energy);
	}

	public void move() {
		Random rand = new Random();
		exposure = exposure.rotate(genotype.getGenes()[rand.nextInt(genotype.getGenes().length)]);
		Vector2d oldPosition = position;
		position = position.add(exposure.toUnitVector(map));
		positionChanged(oldPosition, position);
		int oldEnergy = this.energy;
		this.energy -= map.moveEnergy;
		energyChanged(oldEnergy, this.energy);
	}

	public static Animal breed(Animal parent1, Animal parent2) {

		TorusMap map = parent1.map;
		Vector2d parentPosition = parent1.position;
		Vector2d position;
		ArrayList<Vector2d> availableAdjacentPositions = new ArrayList<>();
		for (MapDirection i: MapDirection.values()) {
			if(!map.isOccupied(parentPosition.add(i.toUnitVector(map)))) {
				availableAdjacentPositions.add(parentPosition.add(i.toUnitVector(map)));
			}
		}
		Random rand = new Random();
		if(availableAdjacentPositions.size() > 0) {
			position = availableAdjacentPositions.get(rand.nextInt(availableAdjacentPositions.size()));
		}
		else {
			position = parentPosition.add(MapDirection.getRandom().toUnitVector(map));
		}
		Animal child = new Animal(map, position, parent1.energy/4 + parent2.energy/4);
		child.genotype = new Genotype(parent1, parent2);
		return child;
	}

	public void eat() {
		int oldEnergy = this.energy;
		this.energy += map.plantEnergy;
		energyChanged(oldEnergy, this.energy);
	}

	public void die() {
		positionFreed(position);
	}

	public void reproduced() {
		int oldEnergy;
		oldEnergy = energy;
		energy -= energy / 4;
		energyChanged(oldEnergy, this.energy);
	}


	public MapDirection getExposure() {
		return exposure;
	}

	public Vector2d getPosition() {
		return position;
	}

	public Genotype getGenotype() {
		return genotype;
	}

	public int getEnergy() {
		return energy;
	}

	public int getID() {
		return ID;
	}


	public void addObserver(IObserverPositions observer) {
		observers.add(observer);
	}

	public void removeObserver(IObserverPositions observer) {
		observers.remove(observer);
	}

	private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
		observers.forEach(observer -> observer.positionChanged(oldPosition, newPosition, this));
	}

	private void positionFreed(Vector2d position) {
		observers.forEach(observer -> observer.positionFreed(position, this));
	}

	private void positionTaken(Vector2d position) {
		observers.forEach(observer -> observer.positionTaken(position, this));
	}

	private void energyChanged(int oldEnergy, int newEnergy) {
		observers.forEach(observer -> observer.energyChanged(oldEnergy, newEnergy, this));
	}



}