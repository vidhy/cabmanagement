package com.phonepe.cabmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonepe.cabmanagement.exception.CityAlreadyExistsException;
import com.phonepe.cabmanagement.model.City;
import com.phonepe.cabmanagement.service.CityService;

@RestController
@RequestMapping({ "/v1/city" })
public class CityController {

	@Autowired
	CityService cityService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> register(@RequestBody City city) {
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			cityService.create(city);
		} catch (CityAlreadyExistsException e) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(httpStatus);
	}

}
