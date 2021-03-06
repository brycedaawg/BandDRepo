package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * <p>Freight cars are designed to handle a variety of goods.
 * For the purposes of this assignment we assume there are three
 * freight car types of interest, characterised by the kinds of
 * goods they are designed to carry:</p>
 * <ul>
 * <li>"G" - General goods</li>
 * <li>"R" - Refrigerated goods</li>
 * <li>"D" - Dangerous materials</li>
 * </ul>
 * 
 * @author Daniel Rablin - n8038848
 **/
public class FreightCar extends RollingStock
{
	private String goods;
	
	/**
	 * Constructs a freight car object.
	 * 
	 * @param grossWeight the freight car's gross weight (fully-laden), in tonnes
	 * @param goodsType the type of goods the car is designed to carry (either
	 * "G", "R" or "D")
	 * @throws TrainException if the gross weight is not positive or if
	 * the goods' type is invalid
	 */
	public FreightCar(Integer grossWeight, String goodsType) throws TrainException
	{
		super(grossWeight);
		
		goodsType = goodsType.toUpperCase();
		
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

	/**
	 * Returns the type of goods this carriage was designed to carry.
	 * (Simulates someone checking the label on the freight car to
	 * determine what's inside.)
	 * 
	 * @return the goodsType (G", "R" or "D")
	 */
	public String goodsType()
	{
		return goods;
	}
	
	/**
	 * Returns a human-readable description of the freight car.  This has the form
	 * "<code>Freight(</code><em>x</em><code>)</code>" where <em>x</em> is a character
	 * ("G", "R" or "D") indicating the type of goods the car is
	 * designed to carry.
	 * 
	 * @return a human-readable description of the freight car
	 */
	@Override public String toString()
	{
		return "Type: Freight Car\nGross Weight: " + getGrossWeight() +
				"\nGoods Type: " + goodsType();
	}
}