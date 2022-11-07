package by.carlab.DAO;

import by.carlab.config.MysqlJdbcDataSource;
import by.carlab.config.MysqlSessionFactory;
import by.carlab.pojo.CarInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao {

    private final MysqlJdbcDataSource dataSource;
    private final SessionFactory sessionFactory;

    public DaoImpl() {
        this(new MysqlJdbcDataSource(),MysqlSessionFactory.getInstance());
        //this.dataSource = new MysqlJdbcDataSource();
        //this.sessionFactory = MysqlSessionFactory.getInstance();
    }

    public DaoImpl(MysqlJdbcDataSource dataSource, SessionFactory sessionFactory) {
        this.dataSource = dataSource;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(CarInfo carInfo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(carInfo);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<CarInfo> readNotes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CarInfo", CarInfo.class).list();
        }
    }

    @Override
    public void update(CarInfo carInfo) {
        create(carInfo);
    }

    @Override
    public void delete(CarInfo carInfo) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(carInfo);
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
            CarInfo carInfo = session.load(CarInfo.class,id);
            session.delete(carInfo);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public CarInfo findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CarInfo.class,id);
        }
    }


    //old way
    /*@Override
    public List<CarInfo> readAll() {
        List<CarInfo> cars = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM t_car");
            while (resultSet.next()) {
                CarInfo carInfo = new CarInfo();
                carInfo.setId(resultSet.getInt("id"));
                carInfo.setBrand(resultSet.getString("brand"));
                carInfo.setFullName(resultSet.getString("full_name"));
                carInfo.setTypeBody(resultSet.getString("type_body"));
                carInfo.setClassAuto(resultSet.getString("class_auto"));
                carInfo.setColor(resultSet.getString("color"));
                carInfo.setEngineDescription(resultSet.getString("engine_description"));
                carInfo.setPrice(resultSet.getDouble("price"));
                carInfo.setPathToImage(resultSet.getString("path_to_image"));
                cars.add(carInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }*/
}
