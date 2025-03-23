package hiber.service;

import hiber.model.Car;

import java.util.Optional;

public interface CarService {
    void add(Car car);
    Optional<Car> findByModelAndSeries(String model, int series);
}
