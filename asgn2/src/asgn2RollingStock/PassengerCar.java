package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * A passenger car is designed to carry people and has a fixed
 * seating capacity.  We assume that the train is a long-distance
 * one in which all passengers are assigned a seat (unlike your 
 * peak-hour, metropolitan commuting experience!).
 * 
 * @author Daniel Rablin - n8038848
 **/
public class PassengerCar extends RollingStock
{
	private Integer seatNum;
	private Integer seatsTaken;

	/**
	 * Constructs a passenger car with a known weight and a
	 * fixed number of seats.  (We allow a passenger car to have
	 * zero seats, although it would not be very useful.)
	 * 
	 * @param grossWeight the carriage's gross weight in tonnes (ignoring the
	 * weight of passengers, which we treat as negligible)
	 * @param numberOfSeats how many seats are available in the carriage
	 * @throws TrainException if the gross weight is not positive or if the
	 * number of seats is negative
	 */
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
	
	/**
	 * Adds the given number of new passengers to the number on board the
	 * carriage.  If there are too many new passengers for the number of
	 * spare seats the left over people are not boarded.
	 * 
	 * @param newPassengers the number of people who wish to board
	 * the carriage
	 * @return the number of people who were unable to board the
	 * carriage because they couldn't get a seat
	 * @throws TrainException if the number of new passengers is negative
	 */
	public Integer board(Integer newPassengers) throws TrainException
	{
		if(newPassengers < 0)
		{
			throw new TrainException("Amount of passengers can not be negative.");
		}
		
		int seatsAvailable = seatNum - seatsTaken;
		if(newPassengers > seatsAvailable)
		{
			//return seatsTaken += newPassengers -= seatNum-seatsTaken;
			seatsTaken = seatNum;
			return newPassengers - seatsAvailable;
		}
		else	
		{
			seatsTaken += newPassengers;
			return 0;
		}
	}
	
	/**
	 * Removes the given number of passengers from this carriage.  Attempting
	 * to remove more passengers than are on board is not allowed.
	 * 
	 * @param departingPassengers the number of passengers alighting
	 * from the carriage
	 * @throws TrainException if the number of departing passengers is
	 * negative or if the number of departing passengers exceeds the
	 * number on board
	 */
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
	
	/**
	 * Returns the number of seats installed on this carriage.
	 * 
	 * @return the number of seats on this carriage
	 */
	public Integer numberOfSeats()
	{
		return seatNum;
	}

	/**
	 * Returns the number of passengers currently on board
	 * this carriage.
	 * 
	 * @return the number of passengers on board
	 */
	public Integer numberOnBoard()
	{
		return seatsTaken;
	}
	
	/**
	 * Returns a human-readable description of the passenger car.  This has the form
	 * "<code>Passenger(</code><em>x</em><code>/</code><em>y</em><code>)</code>" where
	 * <em>x</em> is the number of passengers currently on
	 * board and <em>y</em> is the number of seats in the carriage.
	 * 
	 * 
	 * @return a human-readable description of the passenger car
	 */
	@Override public String toString()
	{
		return "Type: Passenger Car\nGross Weight: " + getGrossWeight() +
				"\nNumber of seats: " + numberOfSeats() +
				"\nNumber of passengers on board: " + numberOnBoard();
	}
}
