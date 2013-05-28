package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * <p>A locomotive is a railway carriage with the ability to
 * propel itself and pull (or push) other carriages.  Thus
 * the primary distinguishing characteristic of a locomotive
 * is how much weight it can pull.
 * </p><p>  
 * However, calculating the total amount of weight a locomotive can
 * move depends on how much "tractive force" it can generate,
 * which in turn depends on the raw horsepower of the engine,
 * the amount of friction between the train's wheels and
 * the track, the track's grade, and whether we are referring
 * to the "starting" force or the "continuous" force.  (As
 * anyone who has ever pushed a stalled car knows,
 * it takes much more effort to get a vehicle moving
 * than to keep it moving once it is already in motion.)  
 * </p><p>
 * Therefore, to keep things simple, most railway operators use
 * a system of discrete "classification codes" to describe how
 * powerful a locomotive is.  For our purposes we adopt a model
 * similar to that used by various UK regional railways in
 * which locomotives are classified by a two-character code:
 * </p><p><ol>
 * <li> A numeric "power class" in the range 1 to 9.
 * </li>
 * <li> An alphabetic "engine type" consisting of either
 * "E" for electric, "D" for diesel or "S" for steam.
 * </li>
 * </ol>
 * </p><p> For instance, a locomotive with classification code
 * "4S" is a steam engine in power class 4.
 * </p><p>
 * To determine how much weight a locomotive can move, we
 * shall use a simple formula in which the maximum weight
 * the locomotive can pull, in tonnes, is its power class
 * times 100.  For instance, a locomotive with classification
 * "4S" can pull at most 4&nbsp;X&nbsp;100&nbsp;=&nbsp;400&nbsp;tonnes.
 * <strong>NB:</strong> This figure includes the weight of the
 * locomotive itself.  Thus a locomotive classified as only "1D"
 * which weighs 180 tonnes cannot move!
 * </p>
 * 
 * @author Daniel Rablin - n8038848
 **/
public class Locomotive extends RollingStock {

	private Integer powerClass;
	private String engineType;
	
	/**
	 * Constructs a new locomotive object with a fixed gross weight and
	 * classification code.
	 * 
	 * @param grossWeight the locomotive's (fully-laden) weight in tonnes
	 * @param classification the locomotive's two-character classification code
	 * @throws TrainException if the locomotive's weight is not strictly
	 * positive or if its classification code is invalid
	 */
	public Locomotive(Integer grossWeight, String classification) throws TrainException
	{
		super(grossWeight);
		
		classification = classification.toUpperCase();
		
		if(classification.length() < 0 || classification.length() > 2 )
		{
			throw new TrainException("Classification code is invalid.");
		}
		
		if(Integer.valueOf(classification.substring(0, 1)) < 1 || Integer.valueOf(classification.substring(0, 1)) > 9)
		{
			throw new TrainException("Power class must be in the range 1 to 9.");
		}
		powerClass = Integer.valueOf(classification.substring(0, 1));
		
		if(classification.substring(1).equals("S") || classification.substring(1).equals("D") || classification.substring(1).equals("E"))
		{
			engineType = classification.substring(1);
		}
		else
		{
			throw new TrainException("Engine type must be 'D' or 'E' or 'S'.");
		}
		
	}
	
	/**
	 * Returns how much total weight the locomotive can pull (including itself),
	 * calculated as explained above.
	 * 
	 * @return the locomotive's "pulling power" in tonnes
	 */
	public Integer power()
	{
		return (powerClass * 100) - getGrossWeight();
	}
	
	/**
	 * Returns a human-readable description of the locomotive.  This has the form
	 * "<code>Loco(</code><em>x</em><code>)</code>" where <em>x</em>
	 * is the locomotive's two-character classification code.
	 * 
	 * @return a human-readable description of the locomotive
	 */
	@Override public String toString()
	{
		return "Type: Locomotive\nGross Weight: " + getGrossWeight() +
				"\nPower Class: " + powerClass.toString() + engineType +
				"\nPullable Weight: " + power(); 
	}
}
