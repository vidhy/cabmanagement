package com.phonepe.cabmanagement.repository;

import java.util.List;

import com.phonepe.cabmanagement.model.Cab;

public interface CabHistoryRepository {

	public void add(Cab cab);

	public List<Cab> get(String cabId);
}
