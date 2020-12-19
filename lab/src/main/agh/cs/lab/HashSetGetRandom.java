package agh.cs.lab;

import java.util.HashMap;
import java.util.Random;

/**
This class represents a structure that stores objects of class Vector2d and supports following operations in O(1):
 - add
 - remove
 - getRandom
**/

public class HashSetGetRandom {
	private Vector2d[] array;
	private HashMap<Vector2d, Integer> hashMap;
	private int lastIndex;
	private Random rand;

	public HashSetGetRandom(int maxCapacity) {
		array = new Vector2d[maxCapacity];
		hashMap = new HashMap<>();
		lastIndex = -1;
		rand = new Random();
	}

	/** Doesn't add element if the structure has already reached its maximum capacity. **/
	public void add(Vector2d position) {
		if(lastIndex+1 > array.length-1)
			return;
		array[++lastIndex] = position;
		hashMap.put(position, lastIndex);
	}

	/** Will skip if position doesn't exist in the structure **/
	public void remove(Vector2d position) {
		Object arrayIndexIsNull = hashMap.get(position);
		if(arrayIndexIsNull == null)
			return;
		hashMap.remove(position);
		int arrayIndex = (int) arrayIndexIsNull;
		array[arrayIndex] = array[lastIndex];
		if(lastIndex > 0)
			hashMap.put(array[arrayIndex], arrayIndex);
		lastIndex--;
	}

	public Vector2d getRandom() {
		int randArrayIndex = rand.nextInt(lastIndex+1);
		return array[randArrayIndex];
	}

	public Vector2d popRandom() {
		Vector2d randomElement = this.getRandom();
		this.remove(randomElement);
		return randomElement;
	}

	public boolean contains(Vector2d position) {
		return hashMap.containsKey(position);
	}

	public int size() {
		return lastIndex+1;
	}

}
