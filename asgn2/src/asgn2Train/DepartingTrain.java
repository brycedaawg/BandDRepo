package asgn2Train;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

/**
 * <p>A train is a sequence of carriages.  This class
 * defines various operations that can be performed to prepare a
 * long-distance train for departure.
 * </p><p>
 * We assume that a train can be assembled from any available
 * rolling stock, including locomotives, passenger cars and
 * freight cars.  However, they may be configured in only a certain
 * sequence:</p>
 * <ol>
 * <li>The first carriage must be a locomotive (and there can be only
 * one locomotive per train).</li>
 * <li>This may be followed by zero or more passenger cars.</li>
 * <li>These may be followed by zero or more freight cars.</li>
 * </ol>
 * <p>
 * Any other configurations of rolling stock are disallowed.
 * </p><p>
 * The process of preparing the train for departure occurs in
 * two stages:
 * </p>
 * <ol>
 * <li>The train is assembled from individual carriages.  New
 * carriages may be added to the rear of the train only.
 * (Similarly, carriages may be removed from the rear of the train
 * only.)</li>
 * <li>Passengers board the train.  For safety reasons, no
 * carriage shunting operations may be performed when any passengers
 * are on board the train.</li>
 * </ol>
 * 
 * @author Bryce Kiefer N8040486
 */
public class DepartingTrain extends Object
{
	private LinkedList<RollingStock> carriages;
	private LinkedList<RollingStock> locomotives;
	private LinkedList<RollingStock> passengerCars;
	private LinkedList<RollingStock> freightCars;
	private Iterator<RollingStock> currentCarriageIterator;
	
	/**
	 * Constructs a (potential) train object containing no carriages
	 * (yet).
	 */
	public DepartingTrain()
	{
		carriages = new LinkedList<RollingStock>();
		locomotives = new LinkedList<RollingStock>();
		passengerCars = new LinkedList<RollingStock>();
		freightCars = new LinkedList<RollingStock>();
	}
	
	/**
	 * <p>Adds a new carriage to the end of the train.  However, a new
	 * carriage may be added only if the resulting train configuration is
	 * valid, as per the rules listed above.
	 * Furthermore, shunting operations may
	 * not be performed if there are passengers on the train.
	 * </p><p>
	 * <strong>Hint:</strong> You may find Java's in-built
	 * <code>instanceof</code> operator useful when implementing this method
	 * (and others in this class).</p>
	 * 
	 * @param newCarriage the new carriage to be added
	 * @throws TrainException if adding the new carriage would produce an
	 * invalid train configuration, or if there are passengers on the train
	 */
	public void addCarriage(RollingStock newCarriage) throws TrainException
	{
		//Get counts for each kind of carriage
		Integer locomotiveCount = 0, passengerCarCount = 0, freightCarCount = 0;
		Iterator<RollingStock> iterator = carriages.iterator();
		while (iterator.hasNext())
		{
			RollingStock carriage = iterator.next();
			if (carriage instanceof Locomotive)			locomotiveCount++;
			if (carriage instanceof PassengerCar)
			{
				if (((PassengerCar)carriage).numberOnBoard() > 0) throw new TrainException("Unable to add locomotive; there are passengers on board this train");
				passengerCarCount++;
			}
			if (carriage instanceof FreightCar)			freightCarCount++;
		}
		
		//Test to see if the new carriage can be added
		if (newCarriage instanceof Locomotive)
		{
			if (!carriages.isEmpty()) throw new TrainException("Unable to add locomotive; one or more carriages have already been added to this train.");
			locomotiveCount++;
			locomotives.add((Locomotive)newCarriage);
		}
		else if (newCarriage instanceof PassengerCar)
		{
			if (locomotiveCount == 0) throw new TrainException("Unable to add passenger car; a locomotive hasn't been added to the train yet.");
			if (freightCarCount > 0) throw new TrainException("Unable to add passenger car; one or more freight cars have already been added to the train.");
			passengerCarCount++;
			PassengerCar p = (PassengerCar)newCarriage;
			
			passengerCars.add(p);
		}
		else if (newCarriage instanceof FreightCar)
		{
			if (locomotiveCount == 0) throw new TrainException("Unable to add freight car; a locomotive hasn't been added to the train yet.");
			freightCarCount++;
			freightCars.add((FreightCar)newCarriage);
		}
		carriages.add(newCarriage);
	}
	
	/**
	 * Adds the given number of people to passenger carriages on
	 * the train.  We do not specify where the passengers must sit, so
	 * they can be allocated to any vacant seat in any passenger car.
	 * 
	 * @param newPassengers the number of people wish to board the train
	 * @return the number of people who were unable to board the train because
	 * they couldn't get a seat
	 * @throws TrainException if the number of new passengers is negative
	 */
	public Integer board(Integer newPassengers) throws TrainException
	{
		if (newPassengers < 0) throw new TrainException("Unable to board passengers; number of new passengers is negative");
		Iterator<RollingStock> iterator = carriages.iterator();
		while (iterator.hasNext() && newPassengers.intValue() > 0)
		{
			RollingStock carriage = iterator.next();
			if (carriage instanceof PassengerCar)
			{
				PassengerCar p = (PassengerCar)carriage;
				newPassengers = p.board(newPassengers);
			}
		}
		return newPassengers;
	}
	
	/**
	 * <p>Returns the first carriage on the train (which must be a
	 * locomotive).  Special value <code>null</code> is returned
	 * if there are no carriages on the train at all.
	 * </p><p>
	 * NB: When combined with method <code>nextCarriage</code>, this
	 * method gives us a simple ability to iteratively examine each of the
	 * train's carriages.
	 * </p>
	 * 
	 * @return the first carriage in the train, or <code>null</code> if
	 * there are no carriages
	 */
	public RollingStock firstCarriage()
	{
		currentCarriageIterator = carriages.iterator();
		if (currentCarriageIterator.hasNext())
		{
			return currentCarriageIterator.next();
		}
		return null;
	}
	
	/**
	 * Returns the next carriage in the train after the one returned
	 * by the immediately preceding call to either this method or
	 * method <code>firstCarriage</code>.  Special value <code>null</code>
	 * is returned if there is no such carriage.  If there has been no
	 * preceding call to either <code>firstCarriage</code> or
	 * <code>nextCarriage</code>, this method behaves like
	 * <code>firstCarriage</code>, i.e., it
	 * returns the first carriage in the train, if any.
	 * </p><p>
	 * NB: When combined with method <code>firstCarriage</code>, this
	 * method gives us a simple ability to iteratively examine each of the
	 * train's carriages.
	 * </p>
	 *  
	 * @return the train's next carriage after the one returned by the
	 * immediately preceding call to either <code>firstCarriage</code> or
	 * <code>nextCarriage</code>, or <code>null</code> if there is no
	 * such carriage
	 */
	public RollingStock nextCarriage()
	{
		if (currentCarriageIterator.hasNext())
		{
			return currentCarriageIterator.next();
		}
		return null;
	}
	
	/**
	 * Returns the total number of seats on the train (whether occupied
	 * or not), counting all passenger cars.
	 * 
	 * @return the number of seats on the train
	 */
	public Integer numberOfSeats()
	{
		Iterator<RollingStock> iterator = carriages.iterator();
		Integer count = new Integer(0);
		while (iterator.hasNext())
		{
			RollingStock carriage = iterator.next();
			if (carriage instanceof PassengerCar)
			{
				PassengerCar p = (PassengerCar)carriage;
				count += p.numberOfSeats();
			}
		}
		return count;
	}
	
	/**
	 * Returns the total number of passengers currently on the train,
	 * counting all passenger cars.
	 * 
	 * @return the number of passengers on the train
	 */
	public Integer numberOnBoard()
	{
		Iterator<RollingStock> iterator = carriages.iterator();
		Integer count = new Integer(0);
		while (iterator.hasNext())
		{
			RollingStock carriage = iterator.next();
			if (carriage instanceof PassengerCar)
			{
				PassengerCar p = (PassengerCar)carriage;
				count += p.numberOnBoard();
			}
		}
		return count;
	}
	
	/**
	 * Removes the last carriage from the train.  (This may be the locomotive if
	 * it is the only item of rolling stock on the train.)  However, shunting
	 * operations may not be performed if there are passengers on the train.
	 * 
	 * @throws TrainException if there is no rolling stock on the "train", or
	 * if there are passengers on the train.
	 */
	public void removeCarriage() throws TrainException
	{
		if (numberOnBoard() > 0) throw new TrainException("Unable to remove carriage; there are passengers on board this train");
		try
		{
			carriages.removeLast();
		}
		catch (NoSuchElementException e)
		{
			throw new TrainException("Unable to remove carriage; there are no carriages to remove");
		}
	}
	
	/**
	 * <p>Returns a human-readable description of the entire train.  This has the
	 * form of a hyphen-separated list of carriages, starting with the
	 * locomotive on the left.  The description is thus a string
	 * "<em>a</em><code>-</code><em>b</em><code>-</code>...<code>-</code><em>z</em>",
	 * where <em>a</em> is the human-readable description of the first carriage
	 * (the locomotive), <em>b</em> is the description of the second carriage,
	 * etc, until the description of the last carriage <em>z</em>.  (Note that
	 * there should be no hyphen after the last carriage.)  For example,
	 * a possible train description may be
	 * "<code>Loco(6D)-Passenger(13/24)-Passenger(16/16)-Freight(G)</code>".
	 * </p><p>
	 * In the degenerate case of a "train" with no carriages, the empty string is
	 * returned.
	 * </p>
	 * 
	 * @return a human-readable description of the entire train
	 */
	public String toString()
	{
		String s = new String();
		Iterator<RollingStock> iterator = carriages.iterator();
		if (iterator.hasNext()) s += iterator.next().toString();
		else return s;
		while (iterator.hasNext())
		{
			s += "-" + iterator.next().toString();
		}
		return s;
	}
	
	/**
	 * <p>Returns whether or not the train is capable of moving.  A train
	 * can move if its locomotive's pulling power equals or exceeds the
	 * train's total weight (including the locomotive itself).
	 * </p><p>
	 * In the degenerate case of a "train" which doesn't have any rolling
	 * stock at all yet, the method returns true.
	 * </p>
	 * 
	 * @return true if the train can move (or contains no carriages), false otherwise
	 */
	public boolean trainCanMove()
	{
		Iterator<RollingStock> iterator = carriages.iterator();
		Integer weight = 0, power = 0;
		while (iterator.hasNext())
		{
			RollingStock carriage = iterator.next();
			if (carriage instanceof Locomotive)
			{
				Locomotive l = (Locomotive)carriage;
				power += l.power();
			}
			weight += carriage.getGrossWeight();
		}
		if (power >= weight) return true;
		return false;
	}
}
