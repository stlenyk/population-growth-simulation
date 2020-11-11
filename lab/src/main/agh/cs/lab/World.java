package agh.cs.lab;

import static java.lang.System.out;

public class World {

	public static void main(String[] args) {
/*
		//Test main.agh.cs.lab.Vector2d
		Vector2d position1 = new Vector2d(1, 2);
		out.println(position1);
		Vector2d position2 = new Vector2d(-2, 1);
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
		for (MoveDirection i : directions)
			zwierz.move(i);
		out.println(zwierz);

		//Test main.agh.cs.lab.OptionsParser
		Animal zwierz = new Animal();
		OptionsParser parser = new OptionsParser();
		MoveDirection[] directions = parser.parse(args);
		for (MoveDirection i : directions)
			zwierz.move(i);
		out.println(zwierz);

		//Test integracyjny main.agh.cs.lab.RectangularMap i Animal
		MoveDirection[] directions = new OptionsParser().parse(args);
		IWorldMap map = new RectangularMap(10, 10);
		map.place(new Animal(map));
		map.place(new Animal(map, new Vector2d(3, 4)));
		map.run(directions);
		out.println(map);
*/
		try {
			//Test Grassfield
			MoveDirection[] directions = new OptionsParser().parse(args);
			IWorldMap map = new GrassField(10);
			map.place(new Animal(map));
			map.place(new Animal(map, new Vector2d(3, 4)));
			map.run(directions);
			out.println(map);
			map.place(new Animal(map, new Vector2d(5, 5)));
			map.place(new Animal(map, new Vector2d(5, 5)));
			out.println("Everything went fine");
		} catch(IllegalArgumentException ex) {
			out.println("Something went wrong");
			out.println();
		}
		out.println("System carried on");
	}


}










