package agh.cs.lab;

import static java.lang.System.out;

public class World {

	public static void main(String[] args) {

/*
		//Test main.agh.cs.lab.Vector2d
		Vector2d position1 = new Vector2d(1,2);
		out.println(position1);
		Vector2d position2 = new Vector2d(-2,1);
		out.println(position2);
		out.println(position1.add(position2));

		//Test main.agh.cs.lab.MapDirection
		MapDirection d = MapDirection.NORTH;
		out.println(d);
		out.println(d.next());
		out.println(d.previous());
		out.println(d.toUnitVector());

		//Test main.agh.cs.lab.Animal
		Animal zwierz = new Animal();
		String[] directionsString = {"r", "f", "21", "f", "f", "f"};
		OptionsParser parser = new OptionsParser();
		MoveDirection[] directions = parser.parse(directionsString);
		for (MoveDirection i: directions)
			zwierz.move(i);
		out.println(zwierz);
*/

		Animal zwierz = new Animal();
		OptionsParser parser = new OptionsParser();
		MoveDirection[] directions = parser.parse(args);
		for(MoveDirection i: directions)
			zwierz.move(i);
		out.println(zwierz);
	}


}
