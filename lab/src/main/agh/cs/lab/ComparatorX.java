package agh.cs.lab;

import java.util.Comparator;

public class ComparatorX implements Comparator<IMapElement> {
	public int compare(IMapElement e1, IMapElement e2) {
		Vector2d v1 = e1.getPosition();
		Vector2d v2 = e2.getPosition();
		if(v1.equals(v2))
			return e1.getClass().getSimpleName().compareTo(e2.getClass().getSimpleName());
		if(v1.x == v2.x)
			return (v1.y < v2.y ? -1 : 1);	// to nie musi być +-1 - można zwracać np. różnicę x'ów
		return (v1.x < v2.x ? -1 : 1);
	}
}
