package com.phonepe.cabmanagement.dto;

import lombok.Data;

@Data
public class CabDto {
	// @NotNull
	private String cityId;
	// @NotNull
	private String id; // unique registration number of cab
	private String modelId;
}
