package com.phonepe.cabmanagement.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIdleTimeDto {
	String cabId;
	Date start;
	Date end;
}
