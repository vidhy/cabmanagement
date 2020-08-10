package com.phonepe.cabmanagement.repository;

import java.util.List;

import com.phonepe.cabmanagement.enums.CabStatus;
import com.phonepe.cabmanagement.model.Cab;

public interface CabRepository {

	public Cab create(Cab cab);

	public void update(Cab cab);

	public Cab get(String id);

	public List<Cab> getCabs(String cityId, CabStatus status);
}
