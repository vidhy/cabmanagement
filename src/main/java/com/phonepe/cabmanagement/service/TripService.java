package com.phonepe.cabmanagement.service;

import com.phonepe.cabmanagement.web.resources.TripRequest;

public interface TripService {

	public TripRequest requestTrip(TripRequest tripDto);

	public TripRequest endTrip(String tripId);

}
