package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
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
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select u from User u join fetch u.car");
        return query.getResultList();
    }

    @Override
    public User getUserAndCarByCarId(int series, String model) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("select user from User user where user.car.series = :series and user.car.model = :model")
                .setParameter("series", series).setParameter("model", model).getSingleResult();

    }

}
