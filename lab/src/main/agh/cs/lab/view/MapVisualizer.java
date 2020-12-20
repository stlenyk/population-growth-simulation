package agh.cs.lab.view;

import agh.cs.lab.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Random;

import static java.lang.System.out;

public class MapVisualizer extends Application {

	private final Color emptySavannaColor = Color.rgb(255, 204, 0);
	private final Color emptyJungleColor = Color.rgb(153, 204, 255);
	private final Color animalColor = Color.rgb(205, 0, 0);
	private final Color plantColor = Color.rgb(0, 128, 0);


	private void drawMap(TorusMap map, GraphicsContext gc, int cellSize, int gapSize) {

		Vector2d cell = new Vector2d(map,0, 0);
		Vector2d moveDown = MapDirection.SOUTH.toUnitVector(map);
		Vector2d moveRight = MapDirection.EAST.toUnitVector(map);
		HashSet<Vector2d> plants = map.getPlants();
		HashSetGetRandom depopulatedAreas = map.getDepopulatedAreas();
		for(int i=0; i<map.height; i++) {
			for(int j=0; j<map.width; j++) {
				if(map.emptyJungle.contains(cell)) {
					gc.setFill(emptyJungleColor);
					gc.fillRect(j * (cellSize+gapSize) + gapSize, i*(cellSize+gapSize) + gapSize, cellSize, cellSize);
				}
				else if(map.emptySavanna.contains(cell)) {
					gc.setFill(emptySavannaColor);
					gc.fillRect(j * (cellSize+gapSize) + gapSize, i*(cellSize+gapSize) + gapSize, cellSize, cellSize);
				}
				else if(!depopulatedAreas.contains(cell)) {
					gc.setFill(animalColor);
					gc.fillOval(j * (cellSize+gapSize) + gapSize, i*(cellSize+gapSize) + gapSize, cellSize, cellSize);
				}
				else if(plants.contains(cell)) {
					gc.setFill(plantColor);
					gc.fillRect(j * (cellSize+gapSize) + gapSize, i*(cellSize+gapSize) + gapSize, cellSize, cellSize);
				}
				cell = cell.add(moveRight);
			}
			cell = cell.add(moveDown);
		}

	}

	@Override
	public void start(final Stage primaryStage) {

		Group root = new Group();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		Canvas canvas = new Canvas(500, 500);
		root.getChildren().add(canvas);

		primaryStage.show();

		SimulationEngine simulationEngine = new SimulationEngine(20, 20, 0.2, 10, 1, 1, 10);
		TorusMap map = simulationEngine.getMap();
		int gapSize = 5;
		int cellSize = (int) ((canvas.getWidth() - gapSize*(map.width+1)) / map.width);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		simulationEngine.placeAnimals();

		new AnimationTimer() {
			@Override
			public void handle(long l) {
				drawMap(simulationEngine.getMap(), gc, cellSize, gapSize);
				simulationEngine.runSimulation();
			}
		}.start();



	}



	public static void main(String[] args) {
		launch(args);
	}
}


//		final long t0 = System.nanoTime();
//		final long oneSecond = (long) 1e9;
//		new AnimationTimer() {
//			@Override
//			public void handle(long now) {
//				drawMap(map, gc, cellSize, gapSize);
//				simulationEngine.runSimulation();
//				if(System.nanoTime() - t0 > 5*oneSecond) {
//					stop();
//				}
//			}
//		}.start();