import maze.Maze;
import maze.InvalidMazeException;
import maze.NoEntranceException;
import maze.NoExitException;
import maze.MultipleEntranceException;
import maze.MultipleExitException;
import maze.RaggedMazeException;


public class MazeDriver{
    public static void main(String args[]){
        try{
        	Maze maze = Maze.fromTxt("../resources/mazes/maze1.txt");
        	System.out.println(maze.toString());
        	Maze.Coordinate c1 = maze.new Coordinate(0,5);
        	Maze.Coordinate c2 = maze.new Coordinate(5,1);
			System.out.println(c1.toString());
        	System.out.println(maze.getTileLocation(maze.getEntrance()));
        	System.out.println(maze.getTileLocation(maze.getExit()));
        	System.out.println(maze.getTileAtLocation(c1));
        	System.out.println(maze.getTileAtLocation(c2));
        	System.out.println("\n");
        	System.out.println(maze.getAdjacentTile(maze.getEntrance(),Maze.Direction.NORTH));
        	System.out.println(maze.getAdjacentTile(maze.getEntrance(),Maze.Direction.SOUTH));
        	System.out.println(maze.getAdjacentTile(maze.getEntrance(),Maze.Direction.EAST));
        	System.out.println(maze.getAdjacentTile(maze.getEntrance(),Maze.Direction.WEST));
        	System.out.println("\n");
        	System.out.println(maze.getAdjacentTile(maze.getExit(),Maze.Direction.NORTH));
        	System.out.println(maze.getAdjacentTile(maze.getExit(),Maze.Direction.SOUTH));
        	System.out.println(maze.getAdjacentTile(maze.getExit(),Maze.Direction.EAST));
        	System.out.println(maze.getAdjacentTile(maze.getExit(),Maze.Direction.WEST));
        }
        catch (NoEntranceException e){
        	System.out.println("\nNo entrance!");
        }
        catch (NoExitException e){
        	System.out.println("\nNo exit!");
        }
        catch (MultipleEntranceException e){
        	System.out.println("\nMultiple entrances!");
        }
        catch (MultipleExitException e){
        	System.out.println("\nMultiple exits!");
        }
        catch (RaggedMazeException e){
        	System.out.println("\nRagged Maze!");
        }
        catch (InvalidMazeException e){
        	System.out.println("\nInvalid Maze!");
        }
    }
}
