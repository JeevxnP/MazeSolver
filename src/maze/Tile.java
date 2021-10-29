package maze;

import java.io.Serializable;

/** Class recording the details of specific tiles in a maze.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class Tile implements Serializable{
    private Type type;
    private boolean encounteredBefore;

    /** Constructor initialises the type for the tile.
    *   @param t: The type of the tile.
    */
    private Tile(Type t){
    	type = t;
    }

    /** Produces a tile from a char.
    *   @param c: The char that represents the tile. 
    *   @return A tile object created from the input char.
    */
    protected static Tile fromChar(char c){
    	Tile t;

    	switch (c){
    		case '.':
    			t = new Tile(Type.CORRIDOR);
    			break;
    	    case 'e':
    			t = new Tile(Type.ENTRANCE);
    			break;
    		case 'x':
    			t = new Tile(Type.EXIT);
    			break;
    		default:
    			t = new Tile(Type.WALL);
    			break;
    	}

    	return t;
    }

    /** Retrieves the type of a tile.
    *   @return The type of the tile.
    */
    public Type getType(){
    	return type;
    }

    /** Determines whether you can navigate to a tile depending on its type.
    *   @return Returns true if you can navigate to the tile (entrance, exit or .), and false if you can not (wall or other).
    */
    public boolean isNavigable(){
        if (type == Type.CORRIDOR || type == Type.ENTRANCE || type == Type.EXIT)
            return true;
        else
            return false;
    }

    /** Converts the tile to its string form.
    *   @return A string which represents the tile.
    */
    public String toString(){
    	switch (type){
    		case CORRIDOR:
    			return ".";
    	    case ENTRANCE:
    			return "e";
    		case EXIT:
    			return "x";
    		default:
    			return "#";
    	}
    }

    /** Retrieves information regarding if the tile has been encountered before.
    *   @return Returns true if it has been encountered before, and false if it has not.
    */
    public boolean getEncountered(){
        return encounteredBefore;
    }

    /** Alters whether the tile has been encountered before or not.
    *   @param b: The value that the encountered variable will be set to.
    */
    public void setEncountered(boolean b){
        encounteredBefore = b;
    }

    /** Enum to represent the different types for a tile.
    *   @author Harjeevan Singh Panesar
    *   @version 5th May 2021
    */
    public enum Type{
		CORRIDOR,ENTRANCE,EXIT,WALL;
	}
}