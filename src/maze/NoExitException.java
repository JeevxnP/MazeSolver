package maze;

/** Class to throw an exception if the maze is not a valid maze because it has no exit.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class NoExitException extends InvalidMazeException{
	/** Creates an instance of no exit exception..
    */	
	public NoExitException(){
		super();
	}

	/** Creates an instance of no exit exception with a message.
    *   @param message: The message for the error if/when it is thrown.
    */
	public NoExitException(String message){
		super(message);
	}

	/** Creates an instance of no exit exception with a message and a cause.
    *   @param message: The message for the error if/when it is thrown.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public NoExitException(String message, Throwable cause){
		super(message, cause);
	}

	/** Creates an instance of no exit exception with a cause.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public NoExitException(Throwable cause){
		super(cause);
	}
}