package asgn2Exceptions;

/**
 * <p>A simple class for exceptions thrown by
 * railway shunting and boarding operations.
 * </p><p>
 * <strong>Hint:</strong> When developing your
 * user interface you may want to consider getting
 * the <code>TrainException</code> constructor to display the
 * problem to the user, rather than catching the
 * exception and then displaying it.</p>
 * 
 * @author Bryce Kiefer - N8040486
 **/

@SuppressWarnings("serial")
public final class TrainException extends Exception
{
	/**
	 * Constructs a new TrainException object.
	 * 
	 * @param message an informative message describing the
	 * cause of the problem
	 */
	public TrainException(String message)
	{
		super(message);
	}
}