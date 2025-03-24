package hiber.dao.impl;

import hiber.dao.CarDao;
import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CarDaoImpl implements CarDao {

    private final SessionFactory sessionFactory;

    private static final String FIND_BY_MODEL_AND_SERIES_QUERY = "FROM Car c WHERE c.model = :model and c.series = :series";

    @Autowired
    public CarDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().persist(car);
    }

    @Override
    public Optional<Car> findByModelAndSeries(String model, int series) {
        Query<Car> queryHQL = sessionFactory.getCurrentSession().createQuery(FIND_BY_MODEL_AND_SERIES_QUERY, Car.class);
        queryHQL.setParameter("model", model);
        queryHQL.setParameter("series", series);

        Car carToReturn = null;
        try {
            carToReturn = queryHQL.getSingleResult();
        } catch (Exception e) {
            System.out.printf("Couldn't find car by model = %s, series = %d", model, series);
        }
        return Optional.ofNullable(carToReturn);
    }
}
