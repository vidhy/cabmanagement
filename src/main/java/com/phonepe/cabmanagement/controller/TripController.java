package com.phonepe.cabmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonepe.cabmanagement.service.TripService;
import com.phonepe.cabmanagement.web.resources.TripRequest;

@RestController
@RequestMapping({ "/v1/trip" })
public class TripController {

	@Autowired
	TripService tripService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<TripRequest> request(@RequestBody TripRequest tripDto) {
		return new ResponseEntity<>(tripService.requestTrip(tripDto), HttpStatus.OK);
	}

	@RequestMapping(value = "/{tripId}/end", method = RequestMethod.POST)
	public ResponseEntity<TripRequest> end(@PathVariable(value = "tripId", required = true) String tripId) {
		return new ResponseEntity<>(tripService.endTrip(tripId), HttpStatus.OK);
	}
}
