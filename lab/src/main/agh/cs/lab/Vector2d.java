package agh.cs.lab;

import java.util.Objects;

public class Vector2d {

	public final int x, y;
	private TorusMap map;

	public Vector2d(TorusMap map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		String s = "(" + this.x + "," + this.y + ")";
		return s;
	}

	public boolean precedes(Vector2d other) {
		return this.x <= other.x && this.y <= other.y;
	}

	public boolean follows(Vector2d other) {
		return this.x >= other.x && this.y >= other.y;
	}

	public Vector2d upperRight(Vector2d other) {
		Vector2d v = new Vector2d(map, Math.max(this.x, other.x), Math.max(this.y, other.y));
		return v;
	}

	public Vector2d lowerLeft(Vector2d other) {
		Vector2d v = new Vector2d(map, Math.min(this.x, other.x), Math.min(this.y, other.y));
		return v;
	}

	public Vector2d add(Vector2d other) {
		Vector2d v = new Vector2d(map, (this.x+other.x + map.width) % map.width, (this.y+other.y + map.height) % map.height);
		return v;
	}

//	public Vector2d subtract(Vector2d other) {
//		Vector2d v = new Vector2d(this.x-other.x, this.y-other.y);
//		return v;
//	}

	@Override
	public boolean equals(Object other){
		if (this == other)
			return true;
		if (!(other instanceof Vector2d))
			return false;
		Vector2d that = (Vector2d) other;
		return this.x == that.x && this.y == that.y;
	}

	public Vector2d opposite() {
		Vector2d v = new Vector2d(map, -this.x, -this.y);
		return v;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

}








































