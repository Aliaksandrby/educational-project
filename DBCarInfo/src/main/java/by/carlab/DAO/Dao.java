package by.carlab.DAO;

import by.carlab.pojo.Car;

import java.util.List;

public interface Dao {
    void create(Car car);
    List<Car> readNotes();
    void update(Car car);
    void delete(Car car);
    void delete(int id);
    Car findById(int id);
}
