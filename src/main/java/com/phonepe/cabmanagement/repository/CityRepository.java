package com.phonepe.cabmanagement.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.phonepe.cabmanagement.model.City;

@Repository
public class CityRepository {

	Map<String, City> cities = new HashMap<>();

	public City create(City city) {
		cities.put(city.getId(), city);
		return city;
	}

	public City get(String id) {
		return cities.get(id);
	}

	public void update(City city) {
		cities.put(city.getId(), city);
	}
}
