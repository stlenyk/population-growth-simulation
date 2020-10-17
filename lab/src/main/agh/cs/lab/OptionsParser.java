package agh.cs.lab;

import java.util.ArrayList;

public class OptionsParser {

	public MoveDirection[] parse(String[] args) {
		ArrayList<MoveDirection> directions = new ArrayList<MoveDirection>();
		for(int i=0; i< args.length; i++) {
			switch (args[i]) {
				case "f":
				case "forward":
					directions.add(MoveDirection.FORWARD);
					break;
				case "b":
				case "backward":
					directions.add(MoveDirection.BACKWARD);
					break;
				case "r":
				case "right":
					directions.add(MoveDirection.RIGHT);
					break;
				case "l":
				case "left":
					directions.add(MoveDirection.LEFT);
					break;
				default:
					break;
			}
		}
		MoveDirection[] directionsArray = new MoveDirection[directions.size()];
		for(int i=0; i<directionsArray.length; i++)
			directionsArray[i] = directions.get(i);
		return directionsArray;
	}

}
