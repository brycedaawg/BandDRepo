package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Daniel Rablin - n8038848
 **/
public class Locomotive extends RollingStock {

	private Integer powerClass;
	private String engineType;
	
	/**
	 * Constructor - Initialises a new Locomotive.
	 * @param grossWeight - A positive integer
	 * @param classification - A Classification for power and engine type (Format: [1-9]['S'||'D'||'E'])
	 * @throws TrainException Exception thrown when: weight is below zero, classification format is wrong, classification does not fit range of allowed values
	 * 
	 * @author Daniel Rablin - n8038848
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
	 * @return Power rating of the locomotive
	 * @see Equation: (Power Class * 100) - Gross Weight
	 * @author Daniel Rablin - n8038848
	 */
	public Integer power()
	{
		return (powerClass * 100) - getGrossWeight();
	}
	
	/**
	 * @return String - human-readable description of the Locomotive
	 */
	@Override public String toString()
	{
		return "Type: Locomotive\nGross Weight: " + getGrossWeight() +
				"\nPower Class: " + powerClass.toString() + engineType +
				"\nPullable Weight: " + power(); 
	}
}
