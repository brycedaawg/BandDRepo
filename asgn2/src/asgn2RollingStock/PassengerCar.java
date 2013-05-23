package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * @author Daniel Rablin - n8038848
 **/
public class PassengerCar extends RollingStock
{
	private Integer seatNum;
	private Integer seatsTaken;
	
	public PassengerCar(Integer grossWeight, Integer numOfSeats) throws TrainException
	{
		super(grossWeight);
		
		//throw exception if seat number is not positive
		if(numOfSeats < 0)
		{
			throw new TrainException("Number of seats cannot be negative.");
		}
		else
		{
			seatNum = numOfSeats;
			seatsTaken = 0;
		}
	}
	
	public Integer board(Integer newPassengers) throws TrainException
	{
		if(newPassengers < 0)
		{
			throw new TrainException("Amount of passengers can not be negative.");
		}
		
		if(newPassengers > (seatNum-seatsTaken))
		{
			return seatsTaken += newPassengers -= seatNum-seatsTaken;
		}
		else	
		{
			seatsTaken += newPassengers;
			return 0;
		}
	}
	
	public void alight(Integer departingPassengers) throws TrainException
	{
		if(departingPassengers < 0)
		{
			throw new TrainException("Amount of departing passengers can not be negative.");
		}
		else if (departingPassengers > numberOnBoard())
		{
			throw new TrainException("Amount of departing passengers can not be greater than the amount of passengers on board.");
		}

		seatsTaken -= departingPassengers;
	}
	
	public Integer numberOfSeats()
	{
		return seatNum;
	}
	
	public Integer numberOnBoard()
	{
		return seatsTaken;
	}
	
	@Override public String toString()
	{
		return "Type: Passenger Car\nGross Weight: " + getGrossWeight() +
				"\nNumber of seats: " + numberOfSeats() +
				"\nNumber of passengers on board: " + numberOnBoard();
	}
}
