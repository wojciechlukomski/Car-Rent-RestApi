package com.lukomski.wojtek.CarRentalApp.services;

import com.lukomski.wojtek.CarRentalApp.db.CarRepository;
import com.lukomski.wojtek.CarRentalApp.db.UserRepository;
import com.lukomski.wojtek.CarRentalApp.exceptions.RentTimeTooLongException;
import com.lukomski.wojtek.CarRentalApp.model.Car;
import com.lukomski.wojtek.CarRentalApp.model.RentCarResponse;
import com.lukomski.wojtek.CarRentalApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }


    public Car add(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAvailableCars() {
        return carRepository.findAllByAvailable(true);
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getById(Integer carId) {
        return carRepository.findByCarId(carId);
    }

    public Car getByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public RentCarResponse rentCar(boolean available, LocalDate dateOfStartRent, LocalDate dateOfEndRent, Integer carId, Integer userId) {
        long daysOfRent = ChronoUnit.DAYS.between(dateOfStartRent, dateOfEndRent);
        if (daysOfRent > 50) {
            throw new RentTimeTooLongException("Rent time is limited to 50 days, please choose shorter period");
        }
        Car car = carRepository.getById(carId);
        User userRentingCar = userRepository.getById(userId);
        userRentingCar.setCar(car);
        userRepository.save(userRentingCar);
        carRepository.updateDateOfRentWithAvailbility(available, dateOfStartRent, dateOfEndRent, carId);
        BigDecimal price = car.getPricePerDay().multiply(new BigDecimal(daysOfRent));
        return new RentCarResponse(car,userRentingCar,price);
    }
}