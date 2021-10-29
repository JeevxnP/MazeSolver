// javaFX imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

// maze imports
import maze.Maze;
import maze.Tile;
import maze.InvalidMazeException;
import maze.NoEntranceException;
import maze.NoExitException;
import maze.MultipleEntranceException;
import maze.MultipleExitException;
import maze.RaggedMazeException;
import maze.visualisation.DisplayTile;
import maze.visualisation.Wall;
import maze.visualisation.Corridor;
import maze.visualisation.EntryOrExit;
import maze.visualisation.Route;
import maze.visualisation.FailedRoute;
import maze.visualisation.ErrorPopUp;
import maze.routing.RouteFinder;
import maze.routing.NoRouteFoundException;

// java.io imports
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;


// Other
import java.util.List;

/**	Class for the user interface using JavaFX
*	@author Harjeevan Singh Panesar
*	@version 5th May 2021
*/
public class MazeApplication extends Application{
	private GridPane mazePane = new GridPane();
	private RouteFinder routeFinder;

	/**	Main entry point for JavaFX application
	*	@param stage: The top-level JavaFX container.
	*/
	@Override
	public void start(Stage stage){
		Label title = new Label("Maze Solving Application");
		title.setLayoutX(200);
		title.setStyle("-fx-font-weight: bold; -fx-background-color: grey;");
		title.setTextFill(Color.web("white"));
		title.setUnderline(true);

		FileChooser fileChooser = new FileChooser();
		Button loadMap = new Button("Load Map");
		loadMap.setOnAction(e -> {
			try{
				String chosenFilePath = fileChooser.showOpenDialog(stage).getPath();
				Maze maze = Maze.fromTxt(chosenFilePath);
				routeFinder = new RouteFinder(maze);
				showMap();
			}
			catch (NoEntranceException exc){
	        	ErrorPopUp.errorBox("Please enter a map which has an entrance tile.", "ERROR LOADING MAP","No Entrance Error");
	        }
	        catch (NoExitException exc){
	        	ErrorPopUp.errorBox("Please enter a map which has an exit tile.", "ERROR LOADING MAP","No Exit Error");
	        }
	        catch (MultipleEntranceException exc){
	        	ErrorPopUp.errorBox("Enter a map which has exactly one entrance tile.", "ERROR LOADING MAP","Multiple Entrance Error");
	        }
	        catch (MultipleExitException exc){
	        	ErrorPopUp.errorBox("Enter a map which has exactly one exit tile.", "ERROR LOADING MAP","Multiple Exit Error");
	        }
	        catch (RaggedMazeException exc){
	        	ErrorPopUp.errorBox("Please enter a rectangular map.", "ERROR LOADING MAP","Ragged Maze Error");
	        }
	        catch (InvalidMazeException exc){
	        	ErrorPopUp.errorBox("Please enter a maze with valid characters.", "ERROR LOADING MAP","Invalid Maze Error");
	        }
		    catch(FileNotFoundException exc){
	        	ErrorPopUp.errorBox("Please check the path of the file.", "ERROR LOADING MAP","File Not Found");
	        }
	        catch(IOException exc){
	        	ErrorPopUp.errorBox("Unable to read the file.", "ERROR LOADING MAP","File Reading Error.");
	        }
	        catch(NullPointerException exc){
	        	// Do nothing - file chooser closed without selecting a file
			}
		});
		HBox loadBox = new HBox(10);
		loadBox.getChildren().addAll(loadMap);

		Label routeButtonsLabel = new Label("Route buttons: ");
		Button fullRoute = new Button("Show Complete Route");
		fullRoute.setOnAction(e -> {
			if (routeFinder == null)
				ErrorPopUp.errorBox("Please load a maze first.", "ROUTE ERROR","No Map Error");
			else{
				try{
					while(!routeFinder.isFinished())
						routeFinder.step();
					ErrorPopUp.errorBox("Route has been found successfully.", "MAZE COMPLETE","Maze Route Found");
				}
				catch(NoRouteFoundException exc){
					ErrorPopUp.errorBox("Could not find a route for this maze.", "ROUTE ERROR","No Route Error");
				}
				finally{
					showMap();
				}
        	}
		});
		Button stepRoute = new Button("Step Through Route");
		stepRoute.setOnAction(e -> {
			if (routeFinder == null)
        		ErrorPopUp.errorBox("Please load a maze first before you step.", "ROUTE ERROR","No Map Error");
	        else{
	        	try{
					routeFinder.step();
					showMap();
					if (routeFinder.isFinished())
	        			ErrorPopUp.errorBox("Route has been found successfully.", "MAZE COMPLETE","Maze Route Found");
	        	}
				catch(NoRouteFoundException exc){
	        		ErrorPopUp.errorBox("Could not find a route for this maze.", "ROUTE ERROR","No Route Error");
				}
	        }
		});

		HBox routeButtonBox = new HBox(10);
		routeButtonBox.getChildren().addAll(routeButtonsLabel,fullRoute,stepRoute);


		Label loadOrSaveButtonsLabel = new Label("Loading/saving a partially complete route buttons: ");
		Button loadRoute = new Button("Load Route");
		loadRoute.setOnAction(e -> {
			try{
				String chosenFilePath = fileChooser.showOpenDialog(stage).getPath();
				routeFinder = RouteFinder.load(chosenFilePath);
				showMap();
			}
			catch(ClassNotFoundException exc){
	        	ErrorPopUp.errorBox("Could not find the class.", "ERROR LOADING MAP","ClassNotFoundException");
			}
			catch(NullPointerException exc){
	        	// Do nothing - file chooser closed without selecting a file
			}
			catch(IOException exc){
	        	ErrorPopUp.errorBox("Try again or try a different file.", "ERROR LOADING MAP","Error Reading Map");
			}
		});
		Button saveRoute = new Button("Save Route");
		saveRoute.setOnAction(e -> {
			try{
				String chosenFilePath = fileChooser.showSaveDialog(stage).getPath();
				routeFinder.save(chosenFilePath);
			}
			catch(NullPointerException exc){
				// Do nothing - file chooser closed or file name not specified
			}

		});
		HBox filesButtonBox = new HBox(10);
		filesButtonBox.getChildren().addAll(loadOrSaveButtonsLabel,loadRoute,saveRoute);

		VBox root = new VBox(10);
		root.getChildren().addAll(title,loadBox,mazePane,routeButtonBox,filesButtonBox);

		// Creates and configures a new scene
		Scene scene = new Scene(root, 750, 750);

		// Adds the scene to the stage and then sets the title
		stage.setScene(scene);
		stage.setTitle("Maze Application");

		// Show the stage
		stage.show();
	}

	/** Updates the maze tiles being displayed on the pane object
	*/
	public void showMap(){
		mazePane.getChildren().clear();

		EntryOrExit tile1;
		Corridor tile2;
		Route tile3;
		FailedRoute tile4;
		Wall tile5;

		String[] mazeTiles = routeFinder.toString().split("\n");
		for (int j=0;j<mazeTiles.length;j++){
			String[] mazeTilesRow = mazeTiles[j].split("");
			for (int i=0;i<mazeTilesRow.length;i++){
				if (mazeTilesRow[i].equals("*")){
					tile3 = new Route();
					mazePane.add(tile3.getTile(),i,j);
				}
				else if (mazeTilesRow[i].equals("-")){
					tile4 = new FailedRoute();
					mazePane.add(tile4.getTile(),i,j);
				}
				else if ((mazeTilesRow[i].equals("e")) || (mazeTilesRow[i].equals("x"))){
					tile1 = new EntryOrExit();
					mazePane.add(tile1.getTile(),i,j);
				}
				else if (mazeTilesRow[i].equals(".")){
					tile2 = new Corridor();
					mazePane.add(tile2.getTile(),i,j);
				}
				else{
					tile5 = new Wall();
					mazePane.add(tile5.getTile(),i,j);
				}
			}
		}
	}

	/** Application main method method - the method is ignored in a correctly deployed JavaFX application.
	*	main() serves only as a fallback in case the application can not be launched.
	*	@param args: The command line arguments
	*/
	public static void main(String[] args){
		launch(args);
	}
}
