package agh.cs.lab;

import static java.lang.Math.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap {

	private int n;
	private Grass[] grasses;

	GrassField(int n) {
		this.n = n;
		width = Integer.MAX_VALUE;
		height = Integer.MAX_VALUE;
		visualize = new MapVisualizer(this);
		generateGrasses();
	}

	private void generateGrasses() {
		HashSet<Vector2d> set = new HashSet<>();
		grasses = new Grass[n];
		int range = (int) sqrt(n*10);
		Random rand = new Random();
		for (int i=0; i<n; i++) {
			Vector2d position = new Vector2d(rand.nextInt(range), rand.nextInt(range));
			while(set.contains(position)) {
				position = new Vector2d(rand.nextInt(range), rand.nextInt(range));
			}
			set.add(position);
			grasses[i] = new Grass(position);
		}
	}

	public Object objectAt(Vector2d position) {
		for(Animal i: animals) {
			if(i.getPosition().equals(position))
				return i;
		}
		for(Grass i: grasses) {
			if(i.getPosition().equals(position))
				return i;
		}
		return null;
	}

	public Vector2d getLowerLeft() {
		Vector2d lowerLeft = new Vector2d(width, height);
		for(Animal i: animals)
			lowerLeft = lowerLeft.lowerLeft(i.getPosition());
		for(Grass i: grasses)
			lowerLeft = lowerLeft.lowerLeft(i.getPosition());
		return lowerLeft;
	}

	public Vector2d getUpperRight() {
		Vector2d upperRight = new Vector2d(0, 0);
		for(Animal i: animals)
			upperRight = upperRight.upperRight(i.getPosition());
		for(Grass i: grasses)
			upperRight = upperRight.upperRight(i.getPosition());
		return upperRight;
	}

}