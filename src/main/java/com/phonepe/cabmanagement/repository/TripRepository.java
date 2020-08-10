package com.phonepe.cabmanagement.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.phonepe.cabmanagement.model.Trip;

public interface TripRepository {

	public String create(Trip trip);

	public void update(Trip trip);

	public Trip get(String id);

	public List<Trip> getTripsWithinRange(String cabId, Date start, Date end);

	public Map<String, Integer> getTripsGroupedByCity();

	public Map<Integer, Integer> getTripsGroupedByHour();
}
