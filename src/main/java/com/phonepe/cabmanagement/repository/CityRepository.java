package com.phonepe.cabmanagement.repository;

import com.phonepe.cabmanagement.model.City;

public interface CityRepository {

	public City create(City city);

	public City get(String id);

	public void update(City city);
}
