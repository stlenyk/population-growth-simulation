package agh.cs.lab;

public interface IObserverPositions {	//IPositionsObserver

	void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal); /** when an animal moves **/ // == positionFreed(oldPosition) + positionTaken(newPosition)

	void positionFreed(Vector2d position, Animal animal); /** when an animal dies **/

	void positionTaken(Vector2d position, Animal animal); /** when an animal is born **/

	void energyChanged(int oldEnergy, int newEnergy, Animal animal); /** when an animal feeds on a plant **/	// jest potrzeba przekazywać nową energię, skoro Pan przekazuje całe zwierzę?


}
