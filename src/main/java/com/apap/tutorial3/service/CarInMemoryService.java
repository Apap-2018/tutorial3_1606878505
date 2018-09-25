package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.CarModel;

/**
 * CarInMemoryService
 * @author Zaki Raihan
 *
 */
@Service
public class CarInMemoryService implements CarService {
	private List<CarModel> archiveCar;
	
	public CarInMemoryService() {
		archiveCar = new ArrayList<>();
	}
	
	@Override
	public void addCar(CarModel car) {
		archiveCar.add(car);
	}
	
	@Override
	public List<CarModel> getCarList() {
		return archiveCar;
	}
	
	@Override
	public CarModel getCarDetail(String id) {
		CarModel receivedCar = null;
		for (CarModel car : archiveCar) {
			if (car.getId().equalsIgnoreCase(id)) {
				receivedCar = car;
			}
		}
		return receivedCar;
	}
	
	@Override
	public CarModel removeCar(String id) {
		CarModel removedCar = this.getCarDetail(id);
		if (removedCar != null) {
			archiveCar.remove(removedCar);
		}
		
		return removedCar;
	}
}
