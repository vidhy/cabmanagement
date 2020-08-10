package com.phonepe.cabmanagement.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.phonepe.cabmanagement.enums.CabStatus;
import com.phonepe.cabmanagement.service.StateService;

@Service
public class StateServiceImpl implements StateService {

	Map<CabStatus, Set<CabStatus>> validStates = new HashMap<>();

	@PostConstruct
	public void initialize() {
		validStates.put(CabStatus.AVAILABLE, new HashSet<>(Arrays.asList(CabStatus.INACTIVE)));
		validStates.put(CabStatus.INACTIVE, new HashSet<>(Arrays.asList(CabStatus.AVAILABLE)));
	}

	@Override
	public boolean isValid(CabStatus currentState, CabStatus targetState) {
		Set<CabStatus> validTargetStates = validStates.get(currentState);
		if (validTargetStates != null && validTargetStates.contains(targetState)) {
			return true;
		}
		return false;
	}

}
