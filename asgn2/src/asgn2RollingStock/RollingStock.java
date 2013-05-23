package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Daniel Rablin - n8038848
 **/
public abstract class RollingStock extends Object
{
	private Integer grossweight;
	
	/**
	 * Constructor - Initialises a new RollingStock.
	 * @param grossWeight - A positive integer
	 * @throws TrainException Exception thrown when grossWeight is below zero.
	 * 
	 * @author Daniel Rablin - n8038848
	 */
	public RollingStock(Integer grossWeight) throws TrainException
	{
		if(grossWeight < 0)
		{
			throw new TrainException("Gross weight cannot be less than zero.");
		}
		grossweight = grossWeight;
	}
	
	/**
	 * @return Integer Gross weight of carriage.
	 * @author Daniel Rablin - n8038848
	 */
	public Integer getGrossWeight()
	{
		return grossweight;
	}
	
	/**
	 * @return String Human-readable description of carraige.
	 * @author Daniel Rablin - n8038848
	 */
	@Override public String toString()
	{
		return "Type: RollingStock.\nGross Weight: " + grossweight.toString();
	}
}
