package com.phonepe.cabmanagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonepe.cabmanagement.dto.TripDto;
import com.phonepe.cabmanagement.enums.CabStatus;
import com.phonepe.cabmanagement.enums.TripStatus;
import com.phonepe.cabmanagement.exception.CityNotFoundException;
import com.phonepe.cabmanagement.model.Cab;
import com.phonepe.cabmanagement.model.Trip;
import com.phonepe.cabmanagement.repository.CityRepository;
import com.phonepe.cabmanagement.repository.TripRepository;
import com.phonepe.cabmanagement.service.CabService;
import com.phonepe.cabmanagement.service.TripService;
import com.phonepe.cabmanagement.strategy.CabAssignmentStrategy;
import com.phonepe.cabmanagement.util.Util;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
	CabAssignmentStrategy cabAssignmentStratety;

	@Autowired
	TripRepository tripRepository;

	@Autowired
	CabService cabService;

	@Autowired
	CityRepository cityRepository;

	public TripDto requestTrip(TripDto tripDto) {
		validate(tripDto);
		Cab cab = cabAssignmentStratety.assignCab(tripDto);
		// update status in db and set cityid as null
		cab.setCityId(null);
		cab.setStatus(CabStatus.ON_TRIP);
		cabService.update(cab);
		// create a trip
		String id = tripRepository.create(getTrip(tripDto, cab));
		tripDto.setId(id);
		tripDto.setCab(cab);
		tripDto.setUserId(Util.getUserId());
		return tripDto;
	}

	private void validate(TripDto tripDto) {
		if (cityRepository.get(tripDto.getFromCityId()) == null || cityRepository.get(tripDto.getToCityId()) == null) {
			throw new CityNotFoundException();
		}
	}

	private Trip getTrip(TripDto tripDto, Cab cab) {
		Trip trip = new Trip();
		trip.setCabId(cab.getId());
		trip.setFromCityId(tripDto.getFromCityId());
		trip.setToCityId(tripDto.getToCityId());
		trip.setStartTime(new Date());
		trip.setStatus(TripStatus.STARTED);
		trip.setUserId(Util.getUserId());
		return trip;
	}

	public TripDto endTrip(String tripId) {
		Trip trip = tripRepository.get(tripId);
		Cab cab = cabService.get(trip.getCabId());
		cab.setStatus(CabStatus.AVAILABLE);
		cab.setCityId(trip.getToCityId());
		cabService.update(cab);
		trip.setStatus(TripStatus.COMPLETED);
		tripRepository.update(trip);
		return getTripDto(trip);
	}

	private TripDto getTripDto(Trip trip) {
		TripDto tripDto = new TripDto();
		tripDto.setFromCityId(trip.getFromCityId());
		tripDto.setId(trip.getId());
		tripDto.setToCityId(trip.getToCityId());
		return tripDto;
	}

	public List<Trip> getTripsWithinRange(String cabId, Date start, Date end) {
		return tripRepository.getTripsWithinRange(cabId, start, end);
	}

}
