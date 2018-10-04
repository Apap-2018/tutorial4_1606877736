package com.apap.tutorial4.service;

import com.apap.tutorial4.model.CarModel;

import java.util.Optional;

public interface CarService {
    Optional<CarModel> getCarById(Long id);
    void addCar(CarModel car);
    void deleteCar(CarModel car);
    void updateCar(Long id, CarModel car);
}
