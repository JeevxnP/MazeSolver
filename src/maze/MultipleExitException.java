package maze;

/** Class to throw an exception if the maze is not a valid maze because it has multiple exits.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class MultipleExitException extends InvalidMazeException{
	/** Creates an instance of multiple exit exception with a message and a cause.
    */	
	public MultipleExitException(){
		super();
	}

	/** Creates an instance of multiple exit exception with a message.
    *   @param message: The message for the error if/when it is thrown.
    */
	public MultipleExitException(String message){
		super(message);
	}

	/** Creates an instance of multiple exit exception with a message and a cause.
    *   @param message: The message for the error if/when it is thrown.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public MultipleExitException(String message, Throwable cause){
		super(message, cause);
	}

	/** Creates an instance of multiple exit exception with a cause.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public MultipleExitException(Throwable cause){
		super(cause);
	}
}