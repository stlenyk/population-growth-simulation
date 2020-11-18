package agh.cs.lab;

import java.util.Comparator;

public class ComparatorY implements Comparator<IMapElement> {
	public int compare(IMapElement e1, IMapElement e2) {
		Vector2d v1 = e1.getPosition();
		Vector2d v2 = e2.getPosition();
		if(v1.equals(v2))
			return e1.getClass().getSimpleName().compareTo(e2.getClass().getSimpleName());
		if(v1.y == v2.y)
			return (v1.x < v2.x ? -1 : 1);
		return (v1.y < v2.y ? -1 : 1);
	}
}
