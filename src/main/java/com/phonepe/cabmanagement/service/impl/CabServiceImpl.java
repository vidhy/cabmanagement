package com.phonepe.cabmanagement.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonepe.cabmanagement.dto.CabDto;
import com.phonepe.cabmanagement.enums.CabStatus;
import com.phonepe.cabmanagement.exception.CabAlreadyExistsException;
import com.phonepe.cabmanagement.exception.CabNotFoundException;
import com.phonepe.cabmanagement.exception.CityNotFoundException;
import com.phonepe.cabmanagement.model.Cab;
import com.phonepe.cabmanagement.repository.CabHistoryRepository;
import com.phonepe.cabmanagement.repository.CabRepository;
import com.phonepe.cabmanagement.repository.CityRepository;
import com.phonepe.cabmanagement.service.CabService;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	CabRepository cabRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CabHistoryRepository cabHistoryRepository;

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
	
	public Duration getIdleTime(String cabId, LocalDateTime start, LocalDateTime end){
		Duration dur = Duration.between(start, end);
		return dur;
	}


}
