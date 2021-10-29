package maze;

/** Class to throw an exception if the maze is not a valid maze because it has no entrance.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class NoEntranceException extends InvalidMazeException{
	/** Creates an instance of no entrance exception.
    */
	public NoEntranceException(){
		super();
	}

	/** Creates an instance of no entrance exception with a message.
    *   @param message: The message for the error if/when it is thrown.
    */
	public NoEntranceException(String message){
		super(message);
	}

	/** Creates an instance of no entrance exception with a message and a cause.
    *   @param message: The message for the error if/when it is thrown.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public NoEntranceException(String message, Throwable cause){
		super(message, cause);
	}

	/** Creates an instance of no entrance exception with a cause.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public NoEntranceException(Throwable cause){
		super(cause);
	}
}