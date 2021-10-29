package maze.visualisation;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/** Class producing a route tile for display on the GUI.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class Route extends DisplayTile{
	/** Constructor initialises a route tile for display.
	*	This is done using the constructor of the superclass 'DisplayTile'.
	*	A fixed colour object (from the JavaFX package) is provided to the super class.
    */
	public Route(){
		super(Color.web("#3399FF"));
	}
}