package com.phonepe.cabmanagement.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.phonepe.cabmanagement.enums.CabStatus;
import com.phonepe.cabmanagement.model.Cab;
import com.phonepe.cabmanagement.repository.CabRepository;

@Repository
public class CabRepositoryImpl implements CabRepository {
	Map<String, Cab> cabs = new HashMap<>();

	public Cab create(Cab cab) {
		cabs.put(cab.getId(), cab);
		Cab copiedCab = new Cab();
		BeanUtils.copyProperties(cab, copiedCab);
		return copiedCab;
	}

	public void update(Cab cab) {
		cabs.put(cab.getId(), cab);
	}

	public Cab get(String id) {
		if (cabs.get(id) == null) {
			return null;
		}
		Cab copiedCab = new Cab();
		BeanUtils.copyProperties(cabs.get(id), copiedCab);
		return copiedCab;
	}

	public List<Cab> getCabs(String cityId, CabStatus status) {

		return cabs.values().stream().filter(
				(cab) -> (cab.getCityId() != null) && cab.getCityId().equals(cityId) && cab.getStatus() == status)
				.collect(Collectors.toList());
	}

}
