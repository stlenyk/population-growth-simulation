package agh.cs.lab.view;

import javafx.application.Application;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Visualizer extends Application {

	private final int simulationsAmount = 1;

	@Override
	public void start(final Stage primaryStage) throws IOException, ParseException {
		MapVisualizer mapVisualizer = new MapVisualizer();
		mapVisualizer.drawSimulation(primaryStage);
		for(int i=0; i<simulationsAmount-1; i++) {
			mapVisualizer.drawSimulation(new Stage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
