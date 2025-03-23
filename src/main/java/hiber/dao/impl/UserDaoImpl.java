package hiber.dao.impl;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

   private final SessionFactory sessionFactory;

   private static final String FIND_BY_CAR_MODEL_AND_SERIES_QUERY =
           "FROM User u WHERE u.car.model = :model and u.car.series = :series";

   @Autowired
   public UserDaoImpl(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().persist(user);
   }

   @Override
   public Optional<User> findByCarModelAndSeries(String model, int series) {
      User user = null;
      try {
         Query<User> queryHQL = sessionFactory.getCurrentSession()
                 .createQuery(FIND_BY_CAR_MODEL_AND_SERIES_QUERY, User.class);
         queryHQL.setParameter("model", model);
         queryHQL.setParameter("series", series);
         user = queryHQL.getSingleResult();
      } catch (Exception e) {
         System.out.printf("Couldn't find user by his car.model = %s, car.series = %d", model, series);
      }
      return Optional.ofNullable(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }

}
