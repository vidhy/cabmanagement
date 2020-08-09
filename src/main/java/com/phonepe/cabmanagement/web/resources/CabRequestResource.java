package com.phonepe.cabmanagement.web.resources;

import java.util.Date;

import com.phonepe.cabmanagement.enums.CabStatus;

import lombok.Data;
import lombok.NonNull;

@Data
public class CabRequestResource {
	//@NotNull
	private String cityId;
	private String regNumber;
	private String model;
	private String capacity;
}
