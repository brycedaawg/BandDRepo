/* @author			Bryce Kiefer
 * 
 */

package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2Train.DepartingTrain;

public class TrainTests {

	static DepartingTrain train;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		train = new DepartingTrain();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Tests to see if a simple locomotive can be added to an empty train without throwing an exception
	 */
	@Test
	public void testAddCarriage_first_locomotive() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
	}
	
	/**
	 * Tests to see if adding a freight car first will produce an exception
	 */
	@Test(expected = TrainException.class)
	public void testAddCarriage_first_freightCar() throws TrainException {
		train.addCarriage(new FreightCar(1000, "G"));
	}
	
	/**
	 * Tests to see if adding a passenger car first will produce an exception
	 */
	@Test(expected = TrainException.class)
	public void testAddCarriage_first_PassengerCar() throws TrainException {
		train.addCarriage(new PassengerCar(1000, 30));
	}
	
	/**
	 * Tests to see if adding a locomotive, passenger car and freight car to an empty train will succeed
	 */
	@Test
	public void testAddCarriage_locomotive_passengerCar_freightCar() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new PassengerCar(1000, 30));
		train.addCarriage(new FreightCar(1000, "G"));
	}
	
	/**
	 * Tests to see if adding a locomotive, freight car and passenger car to an empty train will throw a train exception
	 */
	@Test(expected = TrainException.class)
	public void testAddCarriage_locomotive_freightCar_passengerCar() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new FreightCar(1000, "G"));
		train.addCarriage(new PassengerCar(1000, 30));
	}
	
	/**
	 * Tests to see if adding a car to a valid train with passengers boarded will throw a train exception
	 */
	@Test(expected = TrainException.class)
	public void testAddCarriage_passengersBoarded() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new PassengerCar(1000, 30));
		train.board(1);
		train.addCarriage(new PassengerCar(1000, 30));
		System.out.println("whut");
	}
	
	/**
	 * Tests to see if boarding a negative number of passengers throws a train exception
	 */
	@Test(expected = TrainException.class)
	public void testBoard_negative() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new PassengerCar(1000, 30));
		train.board(-1);
	}
	
	/**
	 * Tests to see if the first carriage method returns the first carriage of the train
	 */
	@Test
	public void testFirstCarriage() throws TrainException {
		Locomotive l = new Locomotive(1500, "4D");
		train.addCarriage(l);
		train.addCarriage(new PassengerCar(1000, 30));
		train.addCarriage(new FreightCar(1000, "G"));
		
		assertEquals(train.firstCarriage(), l);
	}
	
	/**
	 * Tests to see if the first carriage method returns null when there are no carriages added to a train
	 */
	@Test
	public void testFirstCarriage_null() throws TrainException {
		assertEquals(train.firstCarriage(), null);
	}
	
	/**
	 * Tests to see if the next carriage method returns the next carriage of the train
	 */
	@Test
	public void testNextCarriage() throws TrainException {
		Locomotive l = new Locomotive(1500, "4D");
		PassengerCar p = new PassengerCar(1000, 30);
		train.addCarriage(l);
		train.addCarriage(p);
		train.addCarriage(new FreightCar(1000, "G"));
		train.firstCarriage();
		assertEquals(train.nextCarriage(), p);
	}
	
	/**
	 * Tests to see if the next carriage method returns null when it's called more times than there are carriages
	 */
	@Test
	public void testNextCarriage_null() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new PassengerCar(1000, 30));
		train.addCarriage(new FreightCar(1000, "G"));
		train.firstCarriage();
		train.nextCarriage();
		train.nextCarriage();
		assertEquals(train.nextCarriage(), null);
	}
	
	/**
	 * Tests to see if the numberOfSeats method returns the number of seats
	 */
	@Test
	public void testNumberOfSeats() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new PassengerCar(1000, 30));
		train.addCarriage(new PassengerCar(1000, 30));
		assertEquals(train.numberOfSeats().intValue(), 60);
	}
	
	/**
	 * Tests to see if the numberOnBoard method returns the number of passengers boarded
	 */
	@Test
	public void testNumberOnBoard() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new PassengerCar(1000, 30));
		train.board(2);
		train.board(1);
		assertEquals(train.numberOnBoard().intValue(), 3);
	}
	
	/**
	 * Tests to see if the removeCarriage method removes the last carriage of the train
	 */
	@Test
	public void testRemoveCarriage() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.RemoveCarriage();
		assertEquals(train.firstCarriage(), null);
	}
	
	/**
	 * Tests to see if the removeCarriage method throws a train exception when there aren't any carriages to remove
	 */
	@Test(expected = TrainException.class)
	public void testRemoveCarriage_noneToRemove() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.RemoveCarriage();
		train.RemoveCarriage();
	}
	
	/**
	 * Tests to see if the removeCarriage method throws a train exception when there aren't are passengers boarded
	 */
	@Test(expected = TrainException.class)
	public void testRemoveCarriage_boarded() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new PassengerCar(1000, 30));
		train.board(2);
		train.RemoveCarriage();
	}
	
	/**
	 * Tests to see if the to string method works
	 */
	@Test(expected = TrainException.class)
	public void testToString() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new PassengerCar(1000, 30));
		train.addCarriage(new FreightCar(1000, "G"));
		train.board(10);
		train.board(5);
		train.toString();
	}
	
	/**
	 * Tests to see if a train's can move method returns true as expected
	 */
	@Test(expected = TrainException.class)
	public void testTrainCanMove_true() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new PassengerCar(1000, 30));
		train.addCarriage(new FreightCar(1000, "G"));
		assertEquals(train.canMove(), true);
	}
	
	/**
	 * Tests to see if a train's can move method returns false as expected
	 */
	@Test(expected = TrainException.class)
	public void testTrainCanMove_false() throws TrainException {
		train.addCarriage(new Locomotive(1500, "4D"));
		train.addCarriage(new PassengerCar(1000, 30));
		train.addCarriage(new PassengerCar(2000, 30));
		train.addCarriage(new FreightCar(1000, "G"));
		assertEquals(train.canMove(), false);
	}
	
	/**
	 * Tests to see if a train's can move method returns true when no carriages are added to the train
	 */
	@Test(expected = TrainException.class)
	public void testTrainCanMove_noCarriages() throws TrainException {
		assertEquals(train.canMove(), true);
	}
}
