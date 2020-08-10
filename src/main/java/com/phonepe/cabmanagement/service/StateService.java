package com.phonepe.cabmanagement.service;

import com.phonepe.cabmanagement.enums.CabStatus;

public interface StateService {
	public boolean isValid(CabStatus currentState, CabStatus targetState);
}
