package com.phonepe.cabmanagement.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.phonepe.cabmanagement.model.Trip;
import com.phonepe.cabmanagement.repository.TripRepository;

@Repository
public class TripRepositoryImpl implements TripRepository {
	Map<String, Trip> trips = new HashMap<>();

	public String create(Trip trip) {
		String tripId = generateId();
		trip.setId(tripId);
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

	public List<Trip> getTripsWithinRange(String cabId, Date start, Date end) {
		List<Trip> resultTrips = new ArrayList<>();
		resultTrips.stream().filter((t) -> t.getCabId().equals(cabId) && (t.getEndTime().compareTo(start) < 0)
				&& (t.getStartTime().compareTo(end) > 0)).collect(Collectors.toList());
		return resultTrips;
	}

	@Override
	public Map<String, Integer> getTripsGroupedByCity() {
		Map<String, Integer> result = new HashMap<>();
		for (Trip trip : trips.values()) {
			result.putIfAbsent(trip.getFromCityId(), 0);
			result.put(trip.getFromCityId(), result.get(trip.getFromCityId()) + 1);
		}
		return result;
	}

	@Override
	public Map<Integer, Integer> getTripsGroupedByHour() {
		Map<Integer, Integer> result = new HashMap<>();
		// for (Trip trip : trips.values()) {
		// result.putIfAbsent(trip.getFromCityId(), 0);
		// result.put(trip.getFromCityId(), result.get(trip.getFromCityId()) +
		// 1);
		// }
		return result;
	}

}
