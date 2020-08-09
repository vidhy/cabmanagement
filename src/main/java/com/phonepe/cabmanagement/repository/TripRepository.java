package com.phonepe.cabmanagement.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.phonepe.cabmanagement.model.Trip;

@Repository
public class TripRepository {
	Map<String, Trip> trips = new HashMap<>();

	public String create(Trip trip) {
		String tripId = generateId();
		trips.put(tripId, trip);
		return tripId;
	}

	public void update(Trip trip) {
		trips.put(trip.getId(), trip);
	}

	public Trip get(String id) {
		return trips.get(id);
	}

	private String generateId() {
		return Integer.toString(Math.abs(new Random().nextInt()));
	}
}
