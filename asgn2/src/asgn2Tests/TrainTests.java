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
	 * Tests to see if a simple locomotive can be added to an empty train
	 */
	@Test
	public void testAddCarriage_first_locomotive() {
		train.addCarriage(new Locomotive());
	}
	
	/**
	 * Tests to see if adding a freight car first will produce an exception
	 */
	@Test(expected = TrainException.class)
	public void testAddCarriage_first_freightCar() {
		train.addCarriage(new FreightCar());
	}
	
	/**
	 * Tests to see if adding a passenger car first will produce an exception
	 */
	@Test(expected = TrainException.class)
	public void testAddCarriage_first_PassengerCar() {
		train.addCarriage(new PassengerCar());
	}
	
	/**
	 * Tests to see if adding a locomotive, passenger car and freight car to an empty train will succeed
	 */
	@Test
	public void testAddCarriage_locomotive_passengerCar_freightCar() {
		train.addCarriage(new Locomotive());
		train.addCarriage(new PassengerCar());
		train.addCarriage(new FreightCar());
	}
	
	/**
	 * Tests to see if adding a locomotive, freight car and passenger car to an empty train will throw a train exception
	 */
	@Test(expected = TrainException.class)
	public void testAddCarriage_locomotive_freightCar_passengerCar() {
		train.addCarriage(new Locomotive());
		train.addCarriage(new FreightCar());
		train.addCarriage(new PassengerCar());
	}
	
	/**
	 * Tests to see if adding a car to a valid train with passengers boarded will throw a train exception
	 */
	@Test(expected = TrainException.class)
	public void testAddCarriage_passengersBoarded() {
		train.addCarriage(new Locomotive());
		train.addCarriage(new PassengerCar());
		train.board(1);
		train.addCarriage(new PassengerCar());
	}
	
	/**
	 * Tests to see if boarding a negative number of passengers throws a train exception
	 */
	@Test(expected = TrainException.class)
	public void testBoard_negative() {
		train.addCarriage(new Locomotive());
		train.addCarriage(new PassengerCar());
		train.board(-1);
	}
	
	/**
	 * Tests to see if the first carriage method returns the first carriage of the train
	 */
	@Test
	public void testFirstCarriage() {
		Locomotive l = new Locomotive();
		train.addCarriage(l);
		train.addCarriage(new PassengerCar());
		train.addCarriage(new FreightCar());
		
		assertEquals(train.firstCarriage(), l);
	}
	
	/**
	 * Tests to see if the first carriage method returns null when there are no carriages added to a train
	 */
	@Test
	public void testFirstCarriage_null() {
		assertEquals(train.firstCarriage(), null);
	}
}
