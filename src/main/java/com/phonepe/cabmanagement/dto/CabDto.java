package com.phonepe.cabmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabDto {
	// @NotNull
	private String cityId;
	// @NotNull
	private String id; // unique registration number of cab
	private String modelId;

	public CabDto(String cityId, String id) {
		this.cityId = cityId;
		this.id = id;
	}
}
