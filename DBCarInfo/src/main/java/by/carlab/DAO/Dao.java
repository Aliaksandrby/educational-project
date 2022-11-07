package by.carlab.DAO;

import by.carlab.pojo.CarInfo;

import java.util.List;

public interface Dao {
    void create(CarInfo carInfo);
    List<CarInfo> readNotes();
    void update(CarInfo carInfo);
    void delete(CarInfo carInfo);
    void delete(int id);
    CarInfo findById(int id);
    //List<CarInfo> readAll();
}
