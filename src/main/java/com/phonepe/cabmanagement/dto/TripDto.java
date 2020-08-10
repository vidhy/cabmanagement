package com.phonepe.cabmanagement.dto;

import java.util.Date;

import com.phonepe.cabmanagement.model.Cab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
	String id;
	String userId;
	String fromCityId;
	String toCityId;
	Date startTime;
	Date endTime;
	// Location location;
	Cab cab;

	// private class Location {
	// String lat;
	// String longi;
	// }
}
