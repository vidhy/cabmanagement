package com.phonepe.cabmanagement.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phonepe.cabmanagement.dto.CabDto;
import com.phonepe.cabmanagement.service.CabService;

@RestController
@RequestMapping({ "/v1/cab/" })
public class CabController {

	@Autowired
	CabService cabService;

	// register a cab
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> register(@RequestBody CabDto cabDto) {
		cabService.create(cabDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{cabId}/location/{cityId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateLocation(@PathVariable(value = "cabId", required = true) String cabId,
			@PathVariable(value = "cityId", required = true) String cityId) {
		cabService.updateLocation(cabId, cityId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{cabId}/idletime", method = RequestMethod.GET)
	public ResponseEntity<Void> getIdleTime(@PathVariable(value = "cabId", required = true) String cabId,
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
		cabService.getIdleTime(cabId, start, end);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
