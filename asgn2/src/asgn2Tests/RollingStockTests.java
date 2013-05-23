package asgn2Tests; 

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.Locomotive;

public class RollingStockTests
{
	/**
	 * Tests the exception in constructor: RollingStock, using a child class (Locomotive)
	 * Purposely instantiate a locomotive object with a negative Gross Weight.
	 * A TrainException is expected for this test to pass.
	 * @author Daniel Rablin - n8038848
	 */
	@Test(expected=TrainException.class)
	public void ExceptionTest_RollingStockConstructor() throws TrainException
	{
		Integer negativeWeight = -1;
		//bad instantiation should throw exception
		new Locomotive(negativeWeight, "1D");
	}
	
	/**
	 * Tests the abstract method: RollingStock.getGrossWeight(), using a child class (Locomotive)
	 * Set a Gross Weight of 50 and instantiate a Locomotive using that weight.
	 * Assert that the value is equal to the value set in the object when it is returned.
	 * @author Daniel Rablin - n8038848
	 */
	@Test
	public void Test_getGrossWeight() throws TrainException
	{
		//instantiate object
		Integer grossWeight = 50;
		Locomotive test = new Locomotive(grossWeight, "1D");
		//test
		assertEquals(grossWeight, test.getGrossWeight());		
	}
	
	/**
	 * Tests the exception in constructor: Locomotive
	 * Purposely instantiate a locomotive object with a bad classification code.
	 * A TrainException is expected for this test to pass.
	 * @author Daniel Rablin - n8038848
	 */
	@Test(expected=TrainException.class)
	public void ExceptionTest_LocomotiveConstructor() throws TrainException
	{
		Integer weight = 50;
		String badClassification = "0A";
		//bad instantiation should throw exception
		new Locomotive(weight, badClassification);
	}
	
	/**
	 * Tests the method: Locomotive.power()
	 * Instantiate a locomotive with Gross Weight of 50 and Power Class 4S 
	 * The number returned should be equal to the formula defined in the documentation.
	 * (Power Class X 100) - Gross Weight
	 * @author Daniel Rablin - n8038848
	 */
	@Test
	public void Test_LocomotivePower() throws TrainException
	{
		Integer weight = 50;
		String powerClass = "4S";
		Integer power = 4 * 100 - weight;

		//instantiate object
		Locomotive test = new Locomotive(weight, powerClass);
		
		//test Locomotive.power()
		assertTrue(power.intValue() == test.power().intValue());
	}
}
