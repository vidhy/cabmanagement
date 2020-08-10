package com.phonepe.cabmanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonepe.cabmanagement.dto.TripDto;
import com.phonepe.cabmanagement.exception.CabAlreadyExistsException;
import com.phonepe.cabmanagement.exception.CityNotFoundException;
import com.phonepe.cabmanagement.exception.NoAvailableCabsException;
import com.phonepe.cabmanagement.service.TripService;

@RestController
@RequestMapping({ "/v1/trip" })
public class TripController {

	@Autowired
	TripService tripService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<TripDto> request(@RequestBody TripDto tripDto) {
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			tripService.requestTrip(tripDto);
		} catch (CityNotFoundException e) {
			httpStatus = HttpStatus.BAD_REQUEST;
		} catch (NoAvailableCabsException e) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(tripDto, httpStatus);
	}

	@RequestMapping(value = "/{tripId}/end", method = RequestMethod.POST)
	public ResponseEntity<TripDto> end(@PathVariable(value = "tripId", required = true) String tripId) {
		return new ResponseEntity<>(tripService.endTrip(tripId), HttpStatus.OK);
	}

	@RequestMapping(value = "/group/cityId", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Integer>> getTripsGroupedByCity() {
		return new ResponseEntity<>(tripService.getTripsGroupedByCity(), HttpStatus.OK);
	}

	@RequestMapping(value = "/group/hour", method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, Integer>> getTripsGroupedByHour() {
		return new ResponseEntity<>(tripService.getTripsGroupedByHour(), HttpStatus.OK);
	}

}
