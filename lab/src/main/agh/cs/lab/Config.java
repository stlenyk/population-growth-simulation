package agh.cs.lab;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class Config {

	public final int width, height, startEnergy, moveEnergy, plantEnergy, startAnimals;
	public final double jungleRatio;
	private final String path = "config.json";

	public Config() throws IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader(path));
		JSONObject jo = (JSONObject) obj;
		width = ((Long) jo.get("width")).intValue();
		height = ((Long) jo.get("height")).intValue();
		jungleRatio = (Double) jo.get("jungleRatio");
		startEnergy = ((Long) jo.get("startEnergy")).intValue();
		moveEnergy = ((Long) jo.get("moveEnergy")).intValue();
		plantEnergy = ((Long) jo.get("plantEnergy")).intValue();
		startAnimals = ((Long) jo.get("startAnimals")).intValue();

		if(
				width <= 0 ||
				height <= 0 ||
				jungleRatio < 0 || jungleRatio > 1 ||
				startEnergy < 0 ||
				startAnimals < 0 || startAnimals > width * height
		) throw new IOException("Parameters don't match required formatting");

	}

}
