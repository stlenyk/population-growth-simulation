package agh.cs.lab.view;

import agh.cs.lab.*;
import agh.cs.lab.Config;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.SplitPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import static java.lang.System.out;

import java.io.IOException;

public class MapVisualizer {

	private final Color jungleColor = Color.rgb(153, 204, 255);
	private final Color savannaColor = Color.rgb(255, 204, 0);
	private final Color animalColor = Color.rgb(205, 0, 0);
	private final Color plantColor = Color.rgb(0, 128, 0);


	private void drawMap(TorusMap map, GraphicsContext gc, int cellSize, int gapSize) {
		Vector2d cell = new Vector2d(map,0, 0);
		Vector2d moveDown = MapDirection.SOUTH.toUnitVector(map);
		Vector2d moveRight = MapDirection.EAST.toUnitVector(map);
		for(int i=0; i<map.height; i++) {
			for(int j=0; j<map.width; j++) {
				if(map.isJungle(cell)) {
					gc.setFill(jungleColor);
				}
				else {
					gc.setFill(savannaColor);
				}
				gc.fillRect(j * (cellSize+gapSize) + gapSize, i*(cellSize+gapSize) + gapSize, cellSize, cellSize);
				cell = cell.add(moveRight);
			}
			cell = cell.add(moveDown);
		}
	}

	private void drawTurn(TorusMap map, GraphicsContext gc, int cellSize, int gapSize) {
		for(Vector2d cell: map.plants) {
			gc.setFill(plantColor);
			gc.fillOval(cell.x * (cellSize+gapSize) + gapSize, cell.y*(cellSize+gapSize) + gapSize, cellSize, cellSize);
		}
		for(Vector2d cell: map.animals) {
			gc.setFill(animalColor);
			gc.fillOval(cell.x * (cellSize+gapSize) + gapSize, cell.y*(cellSize+gapSize) + gapSize, cellSize, cellSize);
		}
	}

	public void drawSimulation(final Stage stage) throws IOException, ParseException {

		final Config[] config = {new Config()};
		final SimulationEngine[] simulationEngine = {new SimulationEngine(config[0])};
		final TorusMap[] map = {simulationEngine[0].getMap()};
		final int canvasMaxSize = 800;
		final int canvasWidth, canvasHeight;
		if(map[0].width >= map[0].height) {
			canvasWidth = canvasMaxSize;
			canvasHeight = (int) ((double) map[0].height / (double) map[0].width * (double)canvasMaxSize);
		}
		else {
			canvasHeight = canvasMaxSize;
			canvasWidth = (int) ((double) map[0].width / (double) map[0].height * (double)canvasMaxSize);
		}

		Canvas canvas = new Canvas(canvasWidth, canvasHeight);

		int gapSize = (int) (0.1*(double)canvasWidth / (double) map[0].width);
		int cellSize = (int) ((canvas.getWidth() - gapSize*(map[0].width+1)) / map[0].width);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		stage.show();

		simulationEngine[0].placeAnimals();
		drawMap(map[0], gc, cellSize, gapSize);
		drawTurn(map[0], gc, cellSize, gapSize);

		AnimationTimer animationTimer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				drawMap(map[0], gc, cellSize, gapSize);
				drawTurn(map[0], gc, cellSize, gapSize);
				simulationEngine[0].runSimulation();
				if(simulationEngine[0].getTurnCount() == 1e9) {
					stop();
				}
			}
		};
		Button startButton = new Button("start");
		Button stopButton = new Button("stop");
		Button restartButton = new Button("restart");
		Button stepButton = new Button("step");
		startButton.setOnAction(event -> animationTimer.start());
		stopButton.setOnAction(event -> animationTimer.stop());
		restartButton.setOnAction(
				event -> {
					animationTimer.stop();
					simulationEngine[0] = new SimulationEngine(config[0]);
					map[0] = simulationEngine[0].getMap();
					simulationEngine[0].placeAnimals();
					drawMap(map[0], gc, cellSize, gapSize);
					drawTurn(map[0], gc, cellSize, gapSize);
				}
		);
		stepButton.setOnAction(
				event -> {
					simulationEngine[0].runSimulation();
					drawMap(map[0], gc, cellSize, gapSize);
					drawTurn(map[0], gc, cellSize, gapSize);
				}
		);
		VBox buttonVBox = new VBox(startButton, stopButton, restartButton, stepButton);
		SplitPane splitPane = new SplitPane();
		splitPane.getItems().addAll(buttonVBox, canvas);
		Scene scene = new Scene(splitPane);
		stage.setScene(scene);

	}

}
