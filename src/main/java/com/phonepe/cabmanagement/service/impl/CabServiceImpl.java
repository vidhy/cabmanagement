package com.phonepe.cabmanagement.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonepe.cabmanagement.dto.CabDto;
import com.phonepe.cabmanagement.enums.CabStatus;
import com.phonepe.cabmanagement.exception.CabAlreadyExistsException;
import com.phonepe.cabmanagement.exception.CabNotFoundException;
import com.phonepe.cabmanagement.exception.CityNotFoundException;
import com.phonepe.cabmanagement.exception.InvalidStateException;
import com.phonepe.cabmanagement.model.Cab;
import com.phonepe.cabmanagement.model.Trip;
import com.phonepe.cabmanagement.repository.CabHistoryRepository;
import com.phonepe.cabmanagement.repository.CabRepository;
import com.phonepe.cabmanagement.repository.CityRepository;
import com.phonepe.cabmanagement.repository.TripRepository;
import com.phonepe.cabmanagement.service.CabService;
import com.phonepe.cabmanagement.service.StateService;
import com.phonepe.cabmanagement.service.TripService;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	CabRepository cabRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CabHistoryRepository cabHistoryRepository;

	@Autowired
	StateService stateService;

	@Autowired
	TripService tripService;

	public Cab create(CabDto cabDto) {
		// check if cab already exists

		if (cabRepository.get(cabDto.getId()) != null) {
			throw new CabAlreadyExistsException();
		}
		// check if given city exists
		if (cityRepository.get(cabDto.getCityId()) == null) {
			throw new CityNotFoundException();
		}

		// create a cab entry with status as AVAILABLE
		Cab cab = getCab(cabDto);
		cab.setCreatedAt(new Date());
		cab.setUpdatedAt(new Date());
		cabHistoryRepository.add(cab);
		return cabRepository.create(cab);
	}

	public Cab get(String id) {
		Cab cab = cabRepository.get(id);
		if (cab == null) {
			throw new CabNotFoundException();
		}
		return cab;
	}

	public void update(Cab cab) {
		cab.setUpdatedAt(new Date());
		cabHistoryRepository.add(cab);
		cabRepository.update(cab);
	}

	private Cab getCab(CabDto cabDto) {
		Cab cab = new Cab();
		cab.setId(cabDto.getId());
		cab.setCityId(cabDto.getCityId());
		cab.setModelId(cabDto.getModelId());
		cab.setStatus(CabStatus.AVAILABLE);
		return cab;
	}

	public void updateLocation(String cabId, String cityId) {
		if (cityRepository.get(cityId) == null) {
			throw new CityNotFoundException();
		}
		Cab cab = cabRepository.get(cabId);
		if (cab == null) {
			throw new CabNotFoundException();
		}
		cab.setCityId(cityId);
		cabHistoryRepository.add(cab);
		cabRepository.update(cab);
	}

	public void updateStatus(String cabId, CabStatus cabStatus) {
		Cab cab = cabRepository.get(cabId);
		if (!stateService.isValid(cab.getStatus(), cabStatus)) {
			throw new InvalidStateException();
		}
		cab.setStatus(cabStatus);
		cabHistoryRepository.add(cab);
		cabRepository.update(cab);
	}

	public Duration getIdleTime(String cabId, Date start, Date end) {
		Duration dur = Duration.between(start.toInstant(), end.toInstant());
		List<Trip> trips = tripService.getTripsWithinRange(cabId, start, end);
		// #TODO handle cases when trip duration partially overlaps
		for (Trip trip : trips) {
			dur.minus(Duration.between(trip.getStartTime().toInstant(), trip.getEndTime().toInstant()));
		}
		return dur;
	}

	@Override
	public List<Cab> getHistory(String cabId) {
		List<Cab> cabHistory = cabHistoryRepository.get(cabId);
		if (cabHistory == null) {
			throw new CabNotFoundException();
		}
		return cabHistory;
	}

}
