package com.phonepe.cabmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.phonepe.cabmanagement.controller.CabController;
import com.phonepe.cabmanagement.controller.CityController;
import com.phonepe.cabmanagement.dto.CabDto;
import com.phonepe.cabmanagement.model.City;

@SpringBootApplication
public class CabmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabmanagementApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void doSomethingAfterStartup() {
//		CityController cityController = new CityController();
//		City city1 = new City("HYD", "Hyderabad");
//		cityController.register(city1);
//		City city2 = new City("BLR", "Bangalore");
//		cityController.register(city2);
//		City city3 = new City("MUM", "Mumbai");
//		cityController.register(city3);
//
//		CabController cabController = new CabController();
//		CabDto cabDto1 = new CabDto("HYD", "BL07A1234");
//		cabController.register(cabDto1);
//		CabDto cabDto2 = new CabDto("BLR", "BL07B4568");
//		cabController.register(cabDto2);
//		CabDto cabDto3 = new CabDto("MUM", "BL07C8901");
//		cabController.register(cabDto3);
//		System.out.println("hello world, I have just started up");
//	}

}
