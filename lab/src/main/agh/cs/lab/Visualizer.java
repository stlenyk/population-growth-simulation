package agh.cs.lab;

import java.util.HashSet;

import static java.lang.System.out;

public class Visualizer {

	private TorusMap map;

	public Visualizer(TorusMap map) {
		this.map = map;
	}

	public void draw() {
		Vector2d cell = new Vector2d(map,0, 0);
		Vector2d moveDown = MapDirection.SOUTH.toUnitVector(map);
		Vector2d moveRight = MapDirection.EAST.toUnitVector(map);
		HashSet<Vector2d> plants = map.getPlants();
		HashSetGetRandom depopulatedAreas = map.getDepopulatedAreas();
		String s;
		out.print(" ");
		for(int i=0; i<map.width; i++) {
			out.print("_");
		}
		out.println();
		for(int i=0; i<map.height; i++) {
			out.print("|");
			for(int j=0; j<map.width; j++) {
				s = " ";
				if(plants.contains(cell)) {
					s = "*";
				}
				if(!depopulatedAreas.contains(cell)) {
					s = "A";
				}
				out.print(s);
				cell = cell.add(moveRight);
			}
			out.println();
			cell = cell.add(moveDown);
		}
	}

}
