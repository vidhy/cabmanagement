package com.phonepe.cabmanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.phonepe.cabmanagement.dto.TripDto;
import com.phonepe.cabmanagement.model.Trip;

public interface TripService {

	public TripDto requestTrip(TripDto tripDto);

	public TripDto endTrip(String tripId);

	public List<Trip> getTripsWithinRange(String cabId, Date start, Date end);

	public Map<String, Integer> getTripsGroupedByCity();

	public Map<Integer, Integer> getTripsGroupedByHour();

}
