package com.phonepe.cabmanagement.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.phonepe.cabmanagement.enums.CabStatus;
import com.phonepe.cabmanagement.model.Cab;

@Repository
public class CabRepository {
	Map<String, Cab> cabs = new HashMap<>();

	public Cab create(Cab cab) {
		cabs.put(cab.getId(), cab);
		return cab;
	}

	public void update(Cab cab) {
		cabs.put(cab.getId(), cab);
	}

	public Cab get(String id) {
		return cabs.get(id);
	}

	public List<Cab> getCabs(String cityId, CabStatus status) {
		return cabs.values().stream().filter((cab) -> cab.getCityId().equals(cityId) && cab.getStatus() == status)
				.collect(Collectors.toList());
	}
}
