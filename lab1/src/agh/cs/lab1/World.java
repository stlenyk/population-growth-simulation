package agh.cs.lab1;

import static java.lang.System.out;

public class World {

	public static void main(String[] args) {
		out.println("System start");
		run(translate((args)));
		out.println("System stop");
	}

	public static void run(Direction[] args) {
		out.println("Zwierzak idzie do przodu");
		for (int i = 0; i < args.length - 1; i++) {
			out.print(args[i] + ", ");
		}
		out.println(args[args.length - 1]);

		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case FORWARD:
					out.println("przod");
					break;
				case BACKWARD:
					out.println("tyl");
					break;
				case RIGHT:
					out.println("prawo");
					break;
				case LEFT:
					out.println("lewo");
					break;
			}
		}
	}

	public static Direction[] translate(String[] args) {
		Direction[] DirectionsList = new Direction[args.length];
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "f":
					DirectionsList[i] = Direction.FORWARD;
					break;
				case "b":
					DirectionsList[i] = Direction.BACKWARD;
					break;
				case "r":
					DirectionsList[i] = Direction.RIGHT;
					break;
				case "l":
					DirectionsList[i] = Direction.LEFT;
					break;
			}
		}
		return DirectionsList;
	}


}
