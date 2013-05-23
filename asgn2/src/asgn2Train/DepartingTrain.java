package asgn2Train;

import java.util.Iterator;
import java.util.LinkedList;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

public class DepartingTrain
{
	private LinkedList<RollingStock> carriages;
	private LinkedList<RollingStock> locomotives;
	private LinkedList<RollingStock> passengerCars;
	private LinkedList<RollingStock> freightCars;
	private int locomotiveCount = 0, passengerCarCount = 0, freightCarCount = 0, passengers = 0, passengersMax = 0;
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
			passengerCars.add((PassengerCar)newCarriage);
		}
		else if (newCarriage instanceof FreightCar)
		{
			if (locomotiveCount == 0) throw new TrainException("Unable to add freight car; a locomotive hasn't been added to the train yet.");
			freightCarCount++;
			freightCars.add((FreightCar)newCarriage);
		}
		carriages.add(newCarriage);
	}
	public int board(int newPassengers) throws TrainException
	{
		if (newPassengers < 0) throw new TrainException("Unable to board passengers; number of new passengers is negative");
		if (passengers >= passengersMax) return newPassengers;
		{
			int allPassengers = passengers + newPassengers;
			if (allPassengers >= passengersMax)
			{
				passengers = passengersMax;
				return allPassengers - passengersMax;
			}
		}
		passengers += newPassengers;
		return 0;
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

}
