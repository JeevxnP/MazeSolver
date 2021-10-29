package maze;

/** Class to throw an exception if the maze is not a valid maze.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class InvalidMazeException extends Exception{

	/** Creates an instance of invalid maze exception.
    */
	public InvalidMazeException(){
		super();
	}

	/** Creates an instance of invalid maze exception with a message.
    *   @param message: The message for the error if/when it is thrown.
    */
	public InvalidMazeException(String message){
		super(message);
	}

	/** Creates an instance of invalid maze exception with a message and a cause.
    *   @param message: The message for the error if/when it is thrown.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public InvalidMazeException(String message, Throwable cause){
		super(message, cause);
	}

	/** Creates an instance of invalid maze exception with a cause.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public InvalidMazeException(Throwable cause){
		super(cause);
	}
}