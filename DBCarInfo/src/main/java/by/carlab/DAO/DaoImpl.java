package by.carlab.DAO;

import by.carlab.config.MysqlJdbcDataSource;
import by.carlab.config.MysqlSessionFactory;
import by.carlab.pojo.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DaoImpl implements Dao {

    private final MysqlJdbcDataSource dataSource;
    private final SessionFactory sessionFactory;

    public DaoImpl() {
        this(new MysqlJdbcDataSource(),MysqlSessionFactory.getInstance());

    }

    public DaoImpl(MysqlJdbcDataSource dataSource, SessionFactory sessionFactory) {
        this.dataSource = dataSource;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Car car) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(car);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<Car> readNotes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Car", Car.class).list();
        }
    }

    @Override
    public void update(Car car) {
        create(car);
    }

    @Override
    public void delete(Car car) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(car);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Car car = session.load(Car.class,id);
            session.delete(car);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Car findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Car.class,id);
        }
    }
}
