package com.phonepe.cabmanagement.model;

import java.util.Date;

import com.phonepe.cabmanagement.enums.CabStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cab {
	private String id;
	private CabStatus status;
	private String cityId; // null in case cab is on trip
	private String modelId;
	private Date createdAt;
	private Date updatedAt;
}
