package hiber.dao;

import hiber.model.Car;

import java.util.Optional;

public interface CarDao {
    void add(Car car);
    Optional<Car> findByModelAndSeries(String model, int series);
}
