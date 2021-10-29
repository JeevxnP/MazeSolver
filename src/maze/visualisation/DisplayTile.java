package maze.visualisation;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/** Class providing the generic template of a tile for display on the GUI.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public abstract class DisplayTile extends Rectangle{
	protected Rectangle tile;

    /** Constructor initialises the tile for display.
    *   @param c: The colour that the tile will be.
    */
	public DisplayTile(Color c){
		tile = new Rectangle(25.0,25.0,c);
		tile.setArcWidth(15);
		tile.setArcHeight(15);
	}

    /** Retrieves the rectangle object that is the tile.
    *   @return The rectangle object that is the tile.
    */
	public Rectangle getTile(){
		return tile;
	}
}