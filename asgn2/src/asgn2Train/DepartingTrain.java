package asgn2Train;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

public class DepartingTrain extends Object
{
	private LinkedList<RollingStock> carriages;
	private LinkedList<RollingStock> locomotives;
	private LinkedList<RollingStock> passengerCars;
	private LinkedList<RollingStock> freightCars;
	private Iterator<RollingStock> currentCarriageIterator;
	
	public DepartingTrain()
	{
		carriages = new LinkedList<RollingStock>();
		locomotives = new LinkedList<RollingStock>();
		passengerCars = new LinkedList<RollingStock>();
		freightCars = new LinkedList<RollingStock>();
	}
	
	public void addCarriage(RollingStock newCarriage) throws TrainException
	{
		//Get counts for each kind of carriage
		Integer locomotiveCount = 0, passengerCarCount = 0, freightCarCount = 0;
		Iterator<RollingStock> iterator = carriages.iterator();
		while (iterator.hasNext())
		{
			RollingStock carriage = iterator.next();
			if (carriage instanceof Locomotive)			locomotiveCount++;
			if (carriage instanceof PassengerCar)		passengerCarCount++;
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
	public RollingStock firstCarriage()
	{
		currentCarriageIterator = carriages.iterator();
		if (currentCarriageIterator.hasNext())
		{
			return currentCarriageIterator.next();
		}
		return null;
	}
	public RollingStock nextCarriage()
	{
		if (currentCarriageIterator.hasNext())
		{
			return currentCarriageIterator.next();
		}
		return null;
	}
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
	public void RemoveCarriage() throws TrainException
	{
		if (numberOnBoard() > 0) throw new TrainException("Unable tp remove carriage; there are passengers on board this train");
		try
		{
			carriages.removeLast();
		}
		catch (NoSuchElementException e)
		{
			throw new TrainException("Unable tp remove carriage; there are no carriages to remove");
		}
	}
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
	public boolean canMove()
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
