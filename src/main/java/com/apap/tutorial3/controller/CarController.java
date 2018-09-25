package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.CarModel;
import com.apap.tutorial3.service.CarService;

@Controller
public class CarController {
	@Autowired
	private CarService mobilService;
	
	@RequestMapping("/car/add")
	public String add(@RequestParam(value = "id", required = true) String id,
					@RequestParam(value = "brand", required = true) String brand,
					@RequestParam(value = "type", required = true) String type,
					@RequestParam(value = "price", required = true) Long price,
					@RequestParam(value = "amount", required = true) Integer amount) {
		
		CarModel car = new CarModel(id, brand, type, price, amount);
		mobilService.addCar(car);
		return "add";
	}
	
	@RequestMapping("/car/view")
	public String view(@RequestParam("id") String id, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		String checkID = "tidak kosong";
		model.addAttribute("car", archive);
		model.addAttribute("checkID", checkID);
		return "view-car";
	}
	
	@RequestMapping("/car/viewall")
	public String viewall (Model model) {
		List<CarModel> archive = mobilService.getCarList();
		
		model.addAttribute("listCar", archive);
		return "viewall-car";
	}
	
	@RequestMapping(value = {"/car/view/", "/car/view/{id}"})
	public String viewBaru (@PathVariable Optional<String> id, Model model) {
		CarModel archive = null;
		String checkID = null;
		if (id.isPresent()) {
			archive = mobilService.getCarDetail(id.get());
			checkID = "tidak kosong :)";
		}
		
		model.addAttribute("checkID", checkID);
		model.addAttribute("car", archive);
		return "view-car";
	}
	
	@RequestMapping(value = {"/car/update/", "/car/update/{id}/amount/{jumlah}"})
	public String update (@PathVariable Optional<String> id, @PathVariable Optional<Integer> jumlah, Model model) {
		CarModel archive = null;
		String checkID = null;
		if (id.isPresent()) {
			archive = mobilService.getCarDetail(id.get());
			checkID = "tidak kosong :)";
			if (archive != null) {
				archive.setAmount(jumlah.get());
			}
		}
		
		model.addAttribute("checkID", checkID);
		model.addAttribute("car", archive);
		return "update";
	}
	
	@RequestMapping(value = {"/car/delete/", "/car/delete/{id}"})
	public String delete (@PathVariable Optional<String> id, Model model) {
		String checkID = null;
		String checkDelete = null;
		if (id.isPresent()) {
			CarModel removedCar = mobilService.removeCar(id.get());
			checkID = "tidak kosong :)";
			if (removedCar != null) {
				checkDelete = "berhasil delete";
			}
		}
		List<CarModel> archive = mobilService.getCarList();
		model.addAttribute("listCar", archive);
		model.addAttribute("listCarSize", archive.size());
		model.addAttribute("checkID", checkID);
		model.addAttribute("checkDelete", checkDelete);
		return "delete";
	}
	
	
}
