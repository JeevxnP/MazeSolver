package maze.routing;

import maze.Maze;
import maze.Maze.Coordinate;
import maze.Tile;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/** Class to record the details regarding the process of finding a route through the maze.
*	@author Harjeevan Singh Panesar
*	@version 5th May 2021
*/
public class RouteFinder implements Serializable{
	private Maze maze;
	private Stack<Tile> route;
	private boolean finished;
	private boolean started;

    /** Constructor initialises the maze, the stack and the boolean variables of the route finding process.
    *   @param path: The filepath of the input file.
    */
	public RouteFinder(Maze m){
		maze = m;
		route = new Stack<Tile>();
		finished = false;
		started = false;
	}

    /** Retrieves the maze that is having its route found for.
    *   @return The maze as a maze object.
    */
	public Maze getMaze(){
		return maze;
	}

    /** Assigns a stack of tiles to the route.
    *   @param r: The stack that is being assigned to route.
    */
	public void setRoute(Stack<Tile> r){
		route = r;
	}

    /** Assigns a value to finished.
    *   @param f: The boolean value that is being assigned to finished.
    */
	public void setFinished(boolean f){
		finished = f;
	}

    /** Assigns a value to started.
    *   @param s: The boolean value that is being assigned to started.
    */
	public void setStarted(boolean s){
		started = s;
	}

    /** Retrieves the route from the route stack.
    *   @return The route as a list of tiles.
    */
	public List<Tile> getRoute(){
		List<Tile> backwardsRoute = new ArrayList<Tile>();
		while (!route.empty()){
			backwardsRoute.add(route.pop());
		}
		List<Tile> forwardsRoute = new ArrayList<Tile>();
		for (int i=backwardsRoute.size()-1;i>=0;i--){
			forwardsRoute.add(backwardsRoute.get(i));
			route.push(backwardsRoute.get(i));
		}

		return forwardsRoute;
	}

    /** Retrieves the value of finished.
    *   @return The boolean value of finished.
    */
	public boolean isFinished(){
		return finished;
	}

    /** Loads the details of a routefinder object from a serialised file.
    *   @param filepath: The location of the file to be loaded in the users file directory.
    *   @return A routefinder object with the details specified in the file.
    */
	public static RouteFinder load(String filepath) throws ClassNotFoundException, IOException{
		try(
			FileInputStream fileIn = new FileInputStream(filepath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		)
		{
			Maze m = (Maze) objectIn.readObject();
			Stack<Tile> r = (Stack<Tile>) objectIn.readObject();
			boolean f = (boolean) objectIn.readObject();
			boolean s = (boolean) objectIn.readObject();

			RouteFinder routefinder = new RouteFinder(m);
			routefinder.setRoute(r);
			routefinder.setFinished(f);
			routefinder.setStarted(s);

	        objectIn.close();
			return routefinder;
	    }
        catch(IOException e){
        	throw e;
        }
	}

    /** Stores the details of a routefinder object in a serialised file.
    *   @param filepath: The location for where the file should be stored in the users file directory.
    */
	public void save(String filepath){
		try{
			FileOutputStream fileOut = new FileOutputStream(filepath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			objectOut.writeObject(getMaze());
			objectOut.writeObject(route);
			objectOut.writeObject(finished);
			objectOut.writeObject(started);
			objectOut.close();
		}
	    catch(FileNotFoundException e){
            System.out.println("\nNo file was found to write to");
        }
        catch(IOException e){
        	System.out.println("\nThere was a problem writing the file");
        }
	}

    /** Makes a single move towards solving the route for the maze.
    *   @return A boolean, true if a complete route has been found after the current step, and false if it has not.
    */
	public boolean step() throws NoRouteFoundException{
		Maze m = getMaze();
		if ((!started) && (!isFinished())){
			route.push(m.getEntrance());
			started = true;
		}
		else if (!isFinished()){
			Tile northTile = m.getAdjacentTile(route.peek(),Maze.Direction.NORTH);
			Tile eastTile = m.getAdjacentTile(route.peek(),Maze.Direction.EAST);
			Tile southTile = m.getAdjacentTile(route.peek(),Maze.Direction.SOUTH);
			Tile westTile = m.getAdjacentTile(route.peek(),Maze.Direction.WEST);
			if ((northTile != null) && (northTile.isNavigable()) && (route.search(northTile) == -1) && (!northTile.getEncountered())){
				route.push(northTile);
				if (route.peek() == m.getExit())
					finished = true;
			}
			else if ((eastTile != null) && (eastTile.isNavigable()) && (route.search(eastTile) == -1) && (!eastTile.getEncountered())){
				route.push(eastTile);
				if (route.peek() == m.getExit())
					finished = true;
			}
			else if ((southTile != null) && (southTile.isNavigable()) && (route.search(southTile) == -1) && (!southTile.getEncountered())){
				route.push(southTile);
				if (route.peek() == m.getExit())
					finished = true;
			}
			else if ((westTile != null) && (westTile.isNavigable()) && (route.search(westTile) == -1) && (!westTile.getEncountered())){
				route.push(westTile);
				if (route.peek() == m.getExit())
					finished = true;
			}
			else{
				route.peek().setEncountered(true);
				if (route.peek() == m.getEntrance()){
					throw new NoRouteFoundException("Route not found");
				}
				else
					route.pop();
			}
		}

		return isFinished();
	}

    /** Converts the maze associated with a routefinder object to string form.
    *   @return A string which represents the maze.
    */
	public String toString(){
		Maze m = getMaze();
    	ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
		List<Tile> stackItems = getRoute();
		for(int i=0;i<stackItems.size();i++){
			coords.add(m.getTileLocation(stackItems.get(i)));
		}

    	List<List<Tile>> tiles = m.getTiles();

		String output = "";
		boolean inStack;
    	for(int j=0;j<tiles.size();j++){
    		List<Tile> subTiles = tiles.get(j);
    		for(int i=0;i<subTiles.size();i++){
    			inStack = false;
    			for (int k=0;k<coords.size();k++){
    				if ((coords.get(k).getX() == i) && (tiles.size()-coords.get(k).getY()-1 == j))
    					inStack = true;
    			}
    			if (inStack)
    				output += "*";
    			else if (subTiles.get(i).getEncountered())
    				output += "-";
    			else
    				output += subTiles.get(i).toString();
    		}
    		output += "\n";
    	}

    	return output;
	}
}
