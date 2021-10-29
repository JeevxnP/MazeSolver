package maze.visualisation;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/** Class producing a failed path tile for display on the GUI.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class FailedRoute extends DisplayTile{
	/** Constructor initialises a failed path tile for display.
	*	This is done using the constructor of the superclass 'DisplayTile'.
	*	A fixed colour object (from the JavaFX package) is provided to the super class.
    */
	public FailedRoute(){
		super(Color.web("#666666"));
	}
}