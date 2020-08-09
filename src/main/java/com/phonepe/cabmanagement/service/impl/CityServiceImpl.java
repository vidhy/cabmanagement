package com.phonepe.cabmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonepe.cabmanagement.exception.CityAlreadyExistsException;
import com.phonepe.cabmanagement.model.City;
import com.phonepe.cabmanagement.repository.CityRepository;
import com.phonepe.cabmanagement.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	CityRepository cityRepository;

	public City create(City city) {
		// validate city throw exception if city already registered
		if (cityRepository.get(city.getId()) != null) {
			throw new CityAlreadyExistsException();
		}
		return cityRepository.create(city);
	}
}
