package com.phonepe.cabmanagement.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonepe.cabmanagement.dto.CabDto;
import com.phonepe.cabmanagement.dto.GetIdleTimeDto;
import com.phonepe.cabmanagement.enums.CabStatus;
import com.phonepe.cabmanagement.exception.CabAlreadyExistsException;
import com.phonepe.cabmanagement.exception.CabNotFoundException;
import com.phonepe.cabmanagement.exception.CityNotFoundException;
import com.phonepe.cabmanagement.exception.InvalidStateException;
import com.phonepe.cabmanagement.model.Cab;
import com.phonepe.cabmanagement.service.CabService;

@RestController
@RequestMapping({ "/v1/cab/" })
public class CabController {

	@Autowired
	CabService cabService;

	// register a cab
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> register(@RequestBody CabDto cabDto) {
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			cabService.create(cabDto);
		} catch (CabAlreadyExistsException | CityNotFoundException e) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(httpStatus);
	}

	@RequestMapping(value = "/{cabId}/location/{cityId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateLocation(@PathVariable(value = "cabId", required = true) String cabId,
			@PathVariable(value = "cityId", required = true) String cityId) {
		HttpStatus httpstatus = HttpStatus.OK;
		try {
			cabService.updateLocation(cabId, cityId);
		} catch (CabNotFoundException | CityNotFoundException e) {
			httpstatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(httpstatus);
	}

	@RequestMapping(value = "/{cabId}/status/{cabStatus}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateStatus(@PathVariable(value = "cabId", required = true) String cabId,
			@PathVariable(value = "cabStatus", required = true) CabStatus cabStatus) {
		HttpStatus httpstatus = HttpStatus.OK;
		try {
			cabService.updateStatus(cabId, cabStatus);
		} catch (InvalidStateException e) {
			httpstatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(httpstatus);
	}

	@RequestMapping(value = "/{cabId}/idletime", method = RequestMethod.POST)
	public ResponseEntity<Duration> getIdleTime(@RequestBody GetIdleTimeDto getIdleTimeDto) {
		Duration duration = cabService.getIdleTime(getIdleTimeDto.getCabId(), getIdleTimeDto.getStart(),
				getIdleTimeDto.getEnd());
		return new ResponseEntity<>(duration, HttpStatus.OK);
	}

	@RequestMapping(value = "/{cabId}/history", method = RequestMethod.GET)
	public ResponseEntity<List<Cab>> getHistory(@PathVariable String cabId) {
		HttpStatus httpStatus = HttpStatus.OK;
		List<Cab> cabHistory = new ArrayList<Cab>();
		try {
			cabHistory = cabService.getHistory(cabId);
		} catch (CabNotFoundException e) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(cabHistory, httpStatus);
	}
}
