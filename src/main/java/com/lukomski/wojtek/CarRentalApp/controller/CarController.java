package com.lukomski.wojtek.CarRentalApp.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lukomski.wojtek.CarRentalApp.model.Car;
import com.lukomski.wojtek.CarRentalApp.model.RentCarRequest;
import com.lukomski.wojtek.CarRentalApp.model.RentCarResponse;
import com.lukomski.wojtek.CarRentalApp.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(value = "/car/add", consumes = "application/json")
    ResponseEntity<Car> addCar(@RequestBody Car car){ // zwraca nam status czyli Response Entity
        Optional<Car> car1 = Optional.of(carService.add(car)); // Optional jest po to aby wrapowac funkcje ktore moga zwrocic NULLA
        return ResponseEntity.of(car1);

    }
    @GetMapping(value = "/car/all")
    List<Car> allCars (){
        return carService.getAll();
    }

    @GetMapping(value = "/car/{carId}")
    Car specificCar (@PathVariable Integer carId){
        return carService.getById(carId);
    }

    @GetMapping(value = "/car/brand/{brand}")
    Car specificCarByBrand (@PathVariable String brand){
        return carService.getByBrand(brand);
    }

    @GetMapping(value = "/car/available")
    List<Car> availableCars (){
        return carService.getAvailableCars();
    }

    @PostMapping(value = "/car/rent", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RentCarResponse> rent (@RequestBody RentCarRequest rentCarRequest){
        Optional<RentCarResponse> rentCarResponse = Optional.of(carService.rentCar(rentCarRequest.isAvailable(), rentCarRequest.getDateOfStartRent()
                ,rentCarRequest.getDateOfEndRent(), rentCarRequest.getCarId(), rentCarRequest.getUserId()));
        return ResponseEntity.of(rentCarResponse);

    }

}
