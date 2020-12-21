package agh.cs.lab.view;

import agh.cs.lab.*;
import agh.cs.lab.Config;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import static java.lang.System.out;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;

public class MapVisualizer extends Application {

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

		Vector2d cell = new Vector2d(map,0, 0);
		Vector2d moveDown = MapDirection.SOUTH.toUnitVector(map);
		Vector2d moveRight = MapDirection.EAST.toUnitVector(map);
		HashSet<Vector2d> plants = map.getPlants();
		HashSetGetRandom depopulatedAreas = map.getDepopulatedAreas();
		for(int i=0; i<map.height; i++) {
			for(int j=0; j<map.width; j++) {
//				if(!depopulatedAreas.contains(cell)) {
				if(map.animals.contains(cell)) {
					gc.setFill(animalColor);
					gc.fillOval(j * (cellSize+gapSize) + gapSize, i*(cellSize+gapSize) + gapSize, cellSize, cellSize);
				}
				if(plants.contains(cell)) {
					gc.setFill(plantColor);
					gc.fillOval(j * (cellSize+gapSize) + gapSize, i*(cellSize+gapSize) + gapSize, cellSize, cellSize);
				}
				cell = cell.add(moveRight);
			}
			cell = cell.add(moveDown);
		}

	}

	private void drawTurn2(TorusMap map, GraphicsContext gc, int cellSize, int gapSize) {
		for(Vector2d cell: map.plants) {
			gc.setFill(plantColor);
			gc.fillOval(cell.x * (cellSize+gapSize) + gapSize, cell.y*(cellSize+gapSize) + gapSize, cellSize, cellSize);
		}
		for(Vector2d cell: map.animals) {
			gc.setFill(animalColor);
			gc.fillOval(cell.x * (cellSize+gapSize) + gapSize, cell.y*(cellSize+gapSize) + gapSize, cellSize, cellSize);
		}
	}

	@Override
	public void start(final Stage primaryStage) throws IOException, ParseException {

		Group root = new Group();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		SimulationEngine simulationEngine = new SimulationEngine(new Config());
		TorusMap map = simulationEngine.getMap();
		final int canvasMaxSize = 800;
		final int canvasWidth, canvasHeight;
		if(map.width >= map.height) {
			canvasWidth = canvasMaxSize;
			canvasHeight = (int) ((double)map.height / (double)map.width * (double)canvasMaxSize);
		}
		else {
			canvasHeight = canvasMaxSize;
			canvasWidth = (int) ((double)map.width / (double)map.height * (double)canvasMaxSize);
		}

		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		root.getChildren().add(canvas);

		Button start = new Button("Start");



		primaryStage.show();

		int gapSize = (int) (0.1*(double)canvasWidth / (double)map.width);
		int cellSize = (int) ((canvas.getWidth() - gapSize*(map.width+1)) / map.width);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		simulationEngine.placeAnimals();

		final long[] t = {System.nanoTime()};
		new AnimationTimer() {
			@Override
			public void handle(long l) {
				if(simulationEngine.getTurnCount() % 60 == 59)  {
					long t0 = System.nanoTime();
					out.println("FPS\t" + 60.0 / ((double)(t0 - t[0]) / 1000000000.0));
					t[0] = t0;
				}
				drawMap(map, gc, cellSize, gapSize);
				drawTurn2(map, gc, cellSize, gapSize);
				simulationEngine.runSimulation();
				if(simulationEngine.getTurnCount() == 1e9) {
					stop();
				}
			}
		}.start();

	}



	public static void main(String[] args) {
		launch(args);
	}
}
