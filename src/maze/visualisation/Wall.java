package maze.visualisation;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/** Class producing a wall tile for display on the GUI.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class Wall extends DisplayTile{
	/** Constructor initialises a wall tile for display.
	*	This is done using the constructor of the superclass 'DisplayTile'.
	*	A fixed colour object (from the JavaFX package) is provided to the super class.
    */
	public Wall(){
		super(Color.web("#A52A2A"));
	}
}