package asgn2RollingStock;

import asgn2Exceptions.TrainException;

public class FreightCar extends RollingStock
{
	private String goods;
	
	/**
	 * Constructor - Initialises a new Locomotive.
	 * @param newGrossWeight - A positive integer
	 * @param classification - A Classification for power and engine type (Format: [1-9]['S'||'D'||'E'])
	 * @throws TrainException Exception thrown when: weight is below zero, classification format is wrong, classification does not fit range of allowed values
	 * 
	 * @author Daniel Rablin - n8038848
	 */
	public FreightCar(Integer newGrossWeight, String goodsType) throws TrainException
	{
		super(newGrossWeight);
		
		if(goodsType.length() != 1)
		{
			throw new TrainException("Goods Type code is invalid.");
		}
		
		if(goodsType.equals("G") || goodsType.equals("R") || goodsType.equals("D"))
		{
			goods = goodsType;
		}
		else
		{
			throw new TrainException("Goods Type must be 'G' or 'R' or 'D'.");
		}
	}

	public String goodsType()
	{
		return goods;
	}
	
	/**
	 * @return String - human-readable description of the Freight Car
	 */
	@Override public String toString()
	{
		return "Type: Freight Car\nGross Weight: " + getGrossWeight() +
				"\nGoods Type: " + goodsType();
	}
}