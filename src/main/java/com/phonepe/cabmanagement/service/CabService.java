package com.phonepe.cabmanagement.service;

import java.time.Duration;
import java.time.LocalDateTime;

import com.phonepe.cabmanagement.dto.CabDto;
import com.phonepe.cabmanagement.model.Cab;

public interface CabService {
	public Cab create(CabDto cabDto);

	public void update(Cab cab);

	public Cab get(String id);

	public Duration getIdleTime(String cabId, LocalDateTime start, LocalDateTime end);

	public void updateLocation(String cabId, String cityId);
}
