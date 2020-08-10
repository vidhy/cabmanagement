package com.phonepe.cabmanagement.strategy;

import com.phonepe.cabmanagement.dto.TripDto;
import com.phonepe.cabmanagement.exception.NoAvailableCabsException;
import com.phonepe.cabmanagement.model.Cab;

public interface CabAssignmentStrategy {
	public Cab assignCab(TripDto tripRequestDto) throws NoAvailableCabsException;
}
