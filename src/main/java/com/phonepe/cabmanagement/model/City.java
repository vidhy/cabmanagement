package com.phonepe.cabmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
	String id;
	String name;
	String description;

	public City(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
