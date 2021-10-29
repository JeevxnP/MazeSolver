package maze.visualisation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;

/** Class producing a pop up message for the GUI.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class ErrorPopUp{
	/**	Produces a pop up with customised text.
	*	@param infoMessage: The text to be shown in the body of the pop up box.
	*	@param titleBar: The text to be shown in the title bar of the pop up box.
	*	@param headerMessage: The text to be shown as the header of the pop up box.
    */
    public static void errorBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}