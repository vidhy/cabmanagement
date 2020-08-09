package com.phonepe.cabmanagement.model;

import java.util.Date;

import com.phonepe.cabmanagement.enums.TripStatus;

import lombok.Data;

@Data
public class Trip {
	String id;
	String userId;
	String cabId;
	String fromCityId;
	String toCityId;
	TripStatus status;
	Date startTime;
	Date endTime;
}
