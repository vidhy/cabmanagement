package com.phonepe.cabmanagement.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.phonepe.cabmanagement.dto.CabDto;
import com.phonepe.cabmanagement.enums.CabStatus;
import com.phonepe.cabmanagement.model.Cab;

public interface CabService {
	public Cab create(CabDto cabDto);

	public void update(Cab cab);

	public Cab get(String id);

	public void updateLocation(String cabId, String cityId);

	public void updateStatus(String cabId, CabStatus cabStatus);

	public Duration getIdleTime(String cabId, Date start, Date end);

	public List<Cab> getHistory(String cabId);

}
