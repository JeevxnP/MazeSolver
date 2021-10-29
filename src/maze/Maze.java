package maze;

import java.io.FileReader;
import java.io.BufferedReader;

import java.io.Serializable;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;


/** Class to record the details of a maze. Also offers methods that extract various data from the maze.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class Maze implements Serializable{
    private Tile entrance;
    private Tile exit;
    private List<List<Tile>> tiles;

    /** Empty constructor to prevent instances of the maze being produced from outside of this class.
    */
    private Maze(){

    }

    /** Creates an instance of maze base on the information provided from an input file.
    *   @param path: The filepath of the input file.
    *   @return A maze object created from the contents of the input file.
    */
    public static Maze fromTxt(String path) throws InvalidMazeException, NoEntranceException, NoExitException, RaggedMazeException, IOException, FileNotFoundException{
        Maze maze = new Maze();
        maze.tiles = new ArrayList<List<Tile>>();
        Tile entranceTile = null;
        int entranceCounter = 0;
        Tile exitTile = null;
        int exitCounter = 0;

        try(
            FileReader mazeFile = new FileReader(path);
            BufferedReader mazeStream = new BufferedReader(mazeFile);
        )
        {
            String tempMaze = mazeStream.readLine();
            while(tempMaze != null){
                ArrayList<Tile> subTiles = new ArrayList<Tile>();
                char[] split;
                split = tempMaze.toCharArray();
                for(int i=0;i<split.length;i++){
                	if (split[i] == 'e'){
                        entranceTile = Tile.fromChar(split[i]);
                        subTiles.add(entranceTile);
                        entranceCounter += 1;
                	}
                	else if(split[i] == 'x'){
                        exitTile = Tile.fromChar(split[i]);
                        subTiles.add(exitTile);
                        exitCounter += 1;
                    }
                	else if(!(split[i] == '#' || split[i] == '.'))
                		throw new InvalidMazeException("A character that shouldn't be present appears");
                    else
                        subTiles.add(Tile.fromChar(split[i]));
                }
                maze.tiles.add(subTiles);
                tempMaze = mazeStream.readLine();
            }

            if (entranceTile == null)
                throw new NoEntranceException("No entrance");
            else{
                for (int i=0;i<entranceCounter;i++)
                    maze.setEntrance(entranceTile);
            }

            if (exitTile == null)
                throw new NoExitException("No entrance");
            else{
                for (int i=0;i<exitCounter;i++)
                    maze.setExit(exitTile);
            }

            int sizeToCompareWith = 0;
            for(int i=0;i<maze.tiles.size();i++){
                if (i == 0)
                    sizeToCompareWith = maze.tiles.get(i).size();
                else{
                    if (sizeToCompareWith != maze.tiles.get(i).size())
                        throw new RaggedMazeException("Ragged Maze");
                }
            }
        }

        catch (NoEntranceException e){
            throw e;
        }
        catch (NoExitException e){
            throw e;
        }
        catch (MultipleEntranceException e){
            throw e;
        }
        catch (MultipleExitException e){
            throw e;
        }
        catch (RaggedMazeException e){
            throw e;
        }
        catch (InvalidMazeException e){
            throw e;
        }
        catch(FileNotFoundException e){
            throw e;
        }
        catch(IOException e){
            throw e;
        }

        return maze;
    }

    /** Retrives a tile that is next to a specified tile in a given direction.
    *   @param t: The tile in question (the source tile).
    *   @param d: The direction of the tile we want from the source tile.
    *   @return The tile object that is next to the specified tile in the given direction.
    */
    public Tile getAdjacentTile(Tile t, Direction d){
    	Coordinate cIn = getTileLocation(t);
    	int x = cIn.getX();
    	int y = cIn.getY();
    	if (d == Direction.NORTH)
    		y += 1;
    	else if (d == Direction.SOUTH)
    		y -= 1;
    	else if (d == Direction.EAST)
    		x += 1;
    	else if (d == Direction.WEST)
    		x -= 1;

    	Coordinate cOut = new Coordinate(x,y);
    	if (cOut.getX() < 0 || cOut.getX() >= tiles.get(0).size() || cOut.getY() < 0 || cOut.getY() >= tiles.size())
    		return null;
    	else
    		return getTileAtLocation(cOut);
    }

    /** Retrives the entrance of the maze.
    *   @return The entrance as a tile.
    */
    public Tile getEntrance(){
    	return entrance;
    }

    /** Retrives the exit of the maze.
    *   @return The exit as a tile.
    */
    public Tile getExit(){
    	return exit;
    }

    /** Retrieves the tile at a specified location of the maze.
    *   @param c: The coordinate of the tile we want from the maze.
    *   @return The tile at the specified location.
    */
    public Tile getTileAtLocation(Coordinate c){
    	int x = c.getX();
    	int y = tiles.size()-c.getY()-1;
    	return tiles.get(y).get(x);
    }

    /** Retrieves the coordinate of a specified tile in the maze.
    *   @param c: The tile from the maze that we want the coordinates of.
    *   @return The coordinates of the given tile (-1,-1 if the tile is not found).
    */
    public Coordinate getTileLocation(Tile t){
    	int x = -1;
    	int y = -1;
    	for(int i=0;i<tiles.size();i++){
    		x = tiles.get(i).indexOf(t);
    		if(x > -1){
    			y = tiles.size()-i-1;
    			break;
    		}
    	}
    	Coordinate c = new Coordinate(x,y);
    	return c;
    }

    /** Retrieves all the tiles of the maze.
    *   @return The tiles of the maze as a list of lists of tiles.
    */
    public List<List<Tile>> getTiles(){
    	return tiles;
    }

    /** Assigns a tile as the entrance of the maze. Only does so if the entrance hasn't already been set (throws an error),
    *   or if the entrance provided is outside of the maze (doesn't set the entrance).
    *   @param t: The tile which is to be set as the entrance.
    */
    private void setEntrance(Tile t) throws MultipleEntranceException{
    	if (entrance != null)
            throw new MultipleEntranceException("Multiple entrance attempt");
        else{
            Coordinate c = getTileLocation(t);
            if (!(c.getX() < 0 || c.getX() >= tiles.get(0).size() || c.getY() < 0 || c.getY() >= tiles.size()))
                entrance = t;
        }
    }

    /** Assigns a tile as the exit of the maze. Only does so if the exit hasn't already been set (throws an error),
    *   or if the exit provided is outside of the maze (doesn't set the exit).
    *   @param t: The tile which is to be set as the exit.
    */
    private void setExit(Tile t) throws MultipleExitException{
        if (exit != null)
            throw new MultipleExitException("Multiple exit attempt");
        else{
            Coordinate c = getTileLocation(t);
            if (!(c.getX() < 0 || c.getX() >= tiles.get(0).size() || c.getY() < 0 || c.getY() >= tiles.size()))
                exit = t;
        }
    }

    /** Converts the maze to string form.
    *   @return A string which represents the maze.
    */
    public String toString(){
    	String output = "\n";
    	for(int i=0;i<tiles.size();i++){
    		List<Tile> subTiles = tiles.get(i);
    		for(int j=0;j<subTiles.size();j++){
    			output += subTiles.get(j).toString();
    		}
    		output += "\n";
    	}
    	return output;
    }

    /** Class to create a 2d coordinate system for the maze.
    *   @author Harjeevan Singh Panesar
    *   @version 5th May 2021
    */
    public class Coordinate{
    	private int x;
    	private int y;

        /** Constructor initialises the x and y values for the coordinate.
        *   @param i1: The X value fo the coordinate as an integer.
        *   @param i2: The Y value fo the coordinate as an integer.
        */
    	public Coordinate(int i1, int i2){
    		x = i1;
    		y = i2;
    	}

        /** Retrieves the X value of a coordinate as an integer.
        *   @return The X value of the coordinate as an integer.
        */
    	public int getX(){
    		return x;
    	}

        /** Retrieves the Y value of a coordinate as an integer.
        *   @return The Y value of the coordinate as an integer.
        */
    	public int getY(){
    		return y;
    	}

        /** Converts the coordinate to a string in the form "(x, y)".
        *   @return The string that represents the coordinate.
        */
    	public String toString(){
    		String strX = Integer.toString(x);
    		String strY = Integer.toString(y);
    		return "(" + strX + ", " + strY + ")";
    	}
    }

    /** Enum to represent the different directions
    *   @author Harjeevan Singh Panesar
    *   @version 5th May 2021
    */
    public enum Direction{
		NORTH,SOUTH,EAST,WEST;
	}
}