package com.phonepe.cabmanagement.strategy.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phonepe.cabmanagement.enums.CabStatus;
import com.phonepe.cabmanagement.exception.NoAvailableCabsException;
import com.phonepe.cabmanagement.model.Cab;
import com.phonepe.cabmanagement.repository.CabRepository;
import com.phonepe.cabmanagement.strategy.CabAssignmentStrategy;
import com.phonepe.cabmanagement.web.resources.TripRequest;

@Component
public class DefaultCaseAssignmentStrategyImpl implements CabAssignmentStrategy {

	@Autowired
	CabRepository cabRepository;

	@Override
	public Cab assignCab(TripRequest tripDto) throws NoAvailableCabsException {
		// get all available cabs in given city
		List<Cab> candidateCabs = cabRepository.getCabs(tripDto.getFromCityId(), CabStatus.AVAILABLE);
		if (candidateCabs.isEmpty()) {
			throw new NoAvailableCabsException();
		}
		Collections.sort(candidateCabs, (c1, c2) -> c1.getUpdatedAt().compareTo(c2.getUpdatedAt()));
		return candidateCabs.get(0);
	}

}
