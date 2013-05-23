/**
 * @author Daniel Rablin - n8038848
 **/

package asgn2Tests; 

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;

public class RollingStockTests
{
	/**
	 * Tests the exception in constructor: RollingStock, using a child class (Locomotive)
	 * Purposely instantiate a locomotive object with a negative Gross Weight.
	 * A TrainException is expected for this test to pass.
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
	 */
	@Test
	public void Test_getGrossWeight() throws TrainException
	{
		Integer weight = 50;
		
		//instantiate object
		Locomotive locomotive = new Locomotive(weight, "1D");
		
		//test
		assertTrue(weight.equals(locomotive.getGrossWeight()));		
	}
	
	/**
	 * Tests the exception in Locomotive's constructor.
	 * Purposely instantiate a locomotive object with a bad classification code.
	 * A TrainException is expected for this test to pass.
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
	 */
	@Test
	public void Test_LocomotivePower() throws TrainException
	{
		Integer weight = 50;
		String powerClass = "4S";
		Integer testPower = 4 * 100 - weight;

		//instantiate object
		Locomotive locomotive = new Locomotive(weight, powerClass);
		
		//test Locomotive.power()
		assertTrue(testPower.equals(locomotive.power()));
	}
	
	/**
	 * Tests the overrided Locomotive.toString() to make sure no errors are present
	 */
	@Test
	public void Test_LocomotiveToString() throws TrainException
	{
		Integer weight = 50;
		
		//instantiate
		Locomotive locomotive = new Locomotive(weight, "1D");
		//test to catch any random errors
		locomotive.toString();
	}
	
	/**
	 * Tests the method: FreightCar.goodsType()
	 * Instantiate a FreightCar with goodsType 'R'
	 * The string returned should be equal to the set type.
	 */
	@Test
	public void Test_FreightCarGoodsType() throws TrainException
	{
		Integer weight = 50;
		String testGoodsType = "R";

		//instantiate object
		FreightCar freightCar = new FreightCar(weight, testGoodsType);
		
		//test
		assertTrue(testGoodsType.equals(freightCar.goodsType()));
	}
	
	/**
	 * Tests the exception in FreightCar's constructor.
	 * Purposely instantiate a FreightCar object with a bad goodsType code.
	 * A TrainException is expected for this test to pass.
	 */
	@Test(expected=TrainException.class)
	public void ExceptionTest_FreightCarConstructor() throws TrainException
	{
		Integer weight = 50;
		String badGoods = "A";
		//bad instantiation should throw exception
		new FreightCar(weight, badGoods);
	}
	
	/**
	 * Tests the overrided FreightCar.toString() to make sure no errors are present
	 */
	@Test
	public void Test_FreightCarToString() throws TrainException
	{
		Integer weight = 50;
		
		//instantiate
		FreightCar freightCar = new FreightCar(weight, "R");
		//test to catch any random errors
		freightCar.toString();
	}
	
	/**
	 * Tests the exception in PassengerCar's constructor.
	 * Purposely instantiate a PassengerCar object with a negative number of seats.
	 * A TrainException is expected for this test to pass.
	 */
	@Test(expected=TrainException.class)
	public void ExceptionTest_PassengerCarConstructor() throws TrainException
	{
		Integer weight = 50;
		Integer negativeSeatNum = -1;
		
		//bad instantiation should throw exception
		new PassengerCar(weight, negativeSeatNum);
	}
	
	/**
	 * Tests the method: PassengerCar.numberOfSeats()
	 * Instantiate a PassengerCar with number of seats.
	 * The Integer returned should be equal to the set amount.
	 */
	@Test
	public void Test_PassengerCarNumberOfSeats() throws TrainException
	{
		Integer weight = 50;
		Integer seatsNum = 20;

		//instantiate new PassengerCar
		PassengerCar passengerCar = new PassengerCar(weight, seatsNum);
		
		//test
		assertTrue(seatsNum.equals(passengerCar.numberOfSeats()));
	}
	
	/**
	 * Tests the exception in PassengerCar.board(Integer).
	 * Instantiate a PassengerCar object, and then attempt to board a negative amount of passengers.
	 * A TrainException is expected for this test to pass.
	 */
	@Test(expected=TrainException.class)
	public void Test_PassengerCarBoard() throws TrainException
	{
		Integer weight = 50;
		Integer seatsNum = 20;
		Integer negativePassengerNum = -1;

		//instantiate object
		PassengerCar passengerCar = new PassengerCar(weight, seatsNum);
		
		//test - should throw exception
		passengerCar.board(negativePassengerNum);
	}
	
	/**
	 * Tests the PassengerCar.board() method for passenger overflow.
	 * Instantiate a PassengerCar object, then attempt to board more passengers than the seat number;
	 * The number returned from board() should equal the unboarded amount calculated in the test.
	 */
	@Test
	public void test_PassengerCarBoardTooMany() throws TrainException
	{
		Integer weight = 50;
		Integer seatsNum = 20;
		Integer passengerNum = 21;
		Integer unboarded = passengerNum - seatsNum; 
		//instantiate object
		PassengerCar passengerCar = new PassengerCar(weight, seatsNum);
		
		//test - should return 1 unboarded passenger
		assertTrue(unboarded.equals(passengerCar.board(passengerNum)));
	}
	
	/**
	 * Tests the PassengerCar.numberOnBoard() method to see the correct amount of passengers are returned
	 * Instantiate a PassengerCar object, then board some passengers, then see if the amount of passengers returned is equal to the boarded amount.
	 * The number returned from numberOnBoard() should equal the boardingPassengers amount.
	 */
	@Test
	public void Test_PassengerCarNumberOnBoard() throws TrainException
	{
		Integer weight = 50;
		Integer seatsNum = 20;
		Integer passengerNum = 15;
		
		//instantiate object
		PassengerCar passengerCar = new PassengerCar(weight, seatsNum);
		passengerCar.board(passengerNum);
		
		//test
		assertTrue(passengerNum.equals(passengerCar.numberOnBoard()));
	}
	
	/**
	 * Tests the overrided FreightCar.toString() to make sure no errors are present
	 */
	@Test
	public void Test_PassengerCarToString() throws TrainException
	{
		Integer weight = 50;
		Integer seatsNum = 20;
		Integer passengerNum = 15;
		
		//instantiate
		PassengerCar passengerCar = new PassengerCar(weight, seatsNum);
		passengerCar.board(passengerNum);
		//test to catch any random errors
		passengerCar.toString();
	}
}
