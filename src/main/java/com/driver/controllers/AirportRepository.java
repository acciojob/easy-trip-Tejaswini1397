package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.Date;
import java.util.HashMap;

@Repository
public class AirportRepository {
    Map<String,Airport>airportMap=new HashMap<>();
    Map<Integer,Flight>flightMap=new HashMap<>();
    Map<Integer,Passenger>passengerMap=new HashMap<>();

    public void addAirport(Airport airport) {
        //Simply add airport details to your database
        //Return a String message "SUCCESS"
        airportMap.put(airport.getAirportName(),airport);
    }

    public String getLargestAirportName() {
        //Largest airport is in terms of terminals. 3 terminal airport is larger than 2 terminal airport
        //Incase of a tie return the Lexicographically smallest airportName
        Airport largestAirport=null;
        for (Airport airport:airportMap.values()){
            if(largestAirport==null || airport.getNoOfTerminals()>largestAirport.getNoOfTerminals()){
                largestAirport=airport;
            } else if (airport.getNoOfTerminals()==largestAirport.getNoOfTerminals()) {
                if(airport.getAirportName().compareTo(largestAirport.getAirportName())<0){
                    largestAirport=airport;
                }
                
            }
        }
        return (largestAirport!=null)?largestAirport.getAirportName():null;

    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        //Find the duration by finding the shortest flight that connects these 2 cities directly
        //If there is no direct flight between 2 cities return -1.
        for (Flight flight:flightMap.values()){
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity)){
                return flight.getDuration();
            }
        }
        return -1;
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        int count=0;
        //Calculate the total number of people who have flights on that day on a particular airport
        //This includes both the people who have come for a flight and who have landed on an airport after their flight
       for(String people:airportMap.keySet()){

       }
    }

    public int calculateFlightFare(Integer flightId) {
        //Calculation of flight prices is a function of number of people who have booked the flight already.
        //Price for any flight will be : 3000 + noOfPeopleWhoHaveAlreadyBooked*50
        //Suppose if 2 people have booked the flight already : the price of flight for the third person will be 3000 + 2*50 = 3100
        //This will not include the current person who is trying to book, he might also be just checking price
    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        //If the numberOfPassengers who have booked the flight is greater than : maxCapacity, in that case :
        //return a String "FAILURE"
        //Also if the passenger has already booked a flight then also return "FAILURE".
        //else if you are able to book a ticket then return "SUCCESS"
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        //If the passenger has not booked a ticket for that flight or the flightId is invalid or in any other failure case
        // then return a "FAILURE" message
        // Otherwise return a "SUCCESS" message
        // and also cancel the ticket that passenger had booked earlier on the given flightId
        Flight flight = flightMap.get(flightId);
        Passenger passenger=passengerMap.get(passengerId);
        if (flight != null && passenger.equals(passengerId)) {
            flightMap.remove(passengerId);
            passengerMap.remove(passengerId)
            return "SUCCESS";
        }
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        //Tell the count of flight bookings done by a passenger: This will tell the total count of flight bookings done by a passenger :
        int count=0;
        for(int flight:flightMap.keySet()){
            Passenger passenger=passengerMap.get(passengerId);
            if(passenger.equals(passengerId)){
                count++;
            }
        }
        return count;
    }

    public void addFlight(Flight flight) {
        //Return a "SUCCESS" message string after adding a flight.
        flightMap.put(flight.getFlightId(),flight);
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        //We need to get the starting airportName from where the flight will be taking off (Hint think of City variable if that can be of some use)
        //return null incase the flightId is invalid or you are not able to find the airportName
        Flight flight=flightMap.get(flightId);
        if(flight!=null){
            Airport airport=airportMap.get(flightId);
            if(airportMap.get(flightId).equals(flightId))
            return airport.getAirportName();
        }
        return null;
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        //Calculate the total revenue that a flight could have
        //That is of all the passengers that have booked a flight till now and then calculate the revenue
        //Revenue will also decrease if some passenger cancels the flight
        Flight flight=flightMap.get(flightId);
        if(flight!=null){
            int revenue=flight.getFlightId()*calculateFlightFare(flightId);
            return revenue;
        }
        return -1;
    }

    public void addPassenger(Passenger passenger) {
        //Add a passenger to the database
        //And return a "SUCCESS" message if the passenger has been added successfully.
        passengerMap.put(passenger.getPassengerId(),passenger);
    }
}
