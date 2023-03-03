package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }


    @Override
    public User getUserByCar(String model, int series) {
        TypedQuery<Car> queryCar = sessionFactory.getCurrentSession().createQuery("from Car where model = : model AND series = : series");
        queryCar.setParameter("model", model);
        queryCar.setParameter("series", series);

        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where car.id = : car_id")
                .setParameter("car_id", queryCar.getSingleResult().getId());
        return query.getSingleResult();
    }


}
