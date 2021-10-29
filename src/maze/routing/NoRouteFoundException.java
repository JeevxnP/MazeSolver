package maze.routing;

/** Class to throw an exception if a route can not be found for a maze.
*   @author Harjeevan Singh Panesar
*   @version 5th May 2021
*/
public class NoRouteFoundException extends Exception{
	/** Creates an instance of no route found exception.
    *   @param message: The message for the error if/when it is thrown.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public NoRouteFoundException(){

	}

	/** Creates an instance of no route found exception with a message.
    *   @param message: The message for the error if/when it is thrown.
    */
	public NoRouteFoundException(String message){
		super(message);
	}

	/** Creates an instance of no route found exception with a message and a cause.
    *   @param message: The message for the error if/when it is thrown.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public NoRouteFoundException(String message, Throwable cause){
		super(message, cause);
	}

	/** Creates an instance of no route found exception with a cause.
    *	@param cause: A throwable object that was passed to the exception (could be an exception that occurred previously)
    */
	public NoRouteFoundException(Throwable cause){
		super(cause);
	}
}