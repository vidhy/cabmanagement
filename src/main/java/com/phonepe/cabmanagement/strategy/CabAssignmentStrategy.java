package com.phonepe.cabmanagement.strategy;

import com.phonepe.cabmanagement.exception.NoAvailableCabsException;
import com.phonepe.cabmanagement.model.Cab;
import com.phonepe.cabmanagement.web.resources.TripRequest;

public interface CabAssignmentStrategy {
	public Cab assignCab(TripRequest tripRequestDto) throws NoAvailableCabsException;
}
