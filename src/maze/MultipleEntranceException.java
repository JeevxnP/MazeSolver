package maze;

/** Class to throw an exception if the maze is not a valid maze because it has multiple entrances.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class MultipleEntranceException extends InvalidMazeException{
	/** Creates an instance of multiple entrance exception.
    */
	public MultipleEntranceException(){
		super();
	}

	/** Creates an instance of multiple entrance exception with a message.
    *   @param message: The message for the error if/when it is thrown.
    */
	public MultipleEntranceException(String message){
		super(message);
	}

	/** Creates an instance of multiple entrance exception with a message and a cause.
    *   @param message: The message for the error if/when it is thrown.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public MultipleEntranceException(String message, Throwable cause){
		super(message, cause);
	}

	/** Creates an instance of invalid maze exception with a cause.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public MultipleEntranceException(Throwable cause){
		super(cause);
	}
}