package com.phonepe.cabmanagement.web.resources;

import com.phonepe.cabmanagement.model.Cab;

import lombok.Data;

@Data
public class TripRequest {
	String id;
	String userId;
	String fromCityId;
	String toCityId;
	Location location;
	Cab cab;

	private class Location {
		String lat;
		String longi;
	}
}
