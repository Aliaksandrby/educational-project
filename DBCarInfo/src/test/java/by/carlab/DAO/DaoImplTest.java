package by.carlab.DAO;

import by.carlab.pojo.Car;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.*;

public class DaoImplTest extends BaseDB{

    Dao targetObject;

    @Before
    public void setUp() {
        targetObject = new DaoImpl(testMysqlJdbcDataSource,testSessionFactory);
    }

    @After
    public void tearDown() {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void create() {
        //Given
        Connection connection = testMysqlJdbcDataSource.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("select count(*) from t_car;");
        resultSet.next();
        int initialSizeCarInfo = resultSet.getInt(1);
        assertEquals(0, initialSizeCarInfo);



        //When
        Car car = new Car("super car","super body","super engine",
                "super transmission", 1000, "super image",100.55);
        targetObject.create(car);

        //Then
        resultSet = connection.createStatement().executeQuery("select count(*) from t_car;");
        resultSet.next();
        int actualSizeCarInfo = resultSet.getInt(1);
        assertEquals(1, actualSizeCarInfo);

        connection.createStatement().executeUpdate("truncate table t_car;");

        connection.close();
    }

    @Test
    @SneakyThrows
    public void readNotes() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DaoImplTest.class.getResourceAsStream("DaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        List<Car> carList = targetObject.readNotes();

        //Then
        assertEquals(1, carList.size());
        assertEquals("1", carList.get(0).getId().toString());
        assertEquals("volkswagen polo", carList.get(0).getNameCar());
        assertEquals("sedan", carList.get(0).getTypeOfBody());
        assertEquals("gas/1600", carList.get(0).getTypeEngine());
        assertEquals("automatic transmission", carList.get(0).getTypeTransmission());
        assertEquals(9999, carList.get(0).getYearOfIssue());
        assertEquals("image", carList.get(0).getImage());
        assertEquals("100.55", "" + carList.get(0).getPrice());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void update() {
        //TODO: test update, the test create is done
    }

    @Test
    @SneakyThrows
    public void deleteObj() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DaoImplTest.class.getResourceAsStream("DaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Car car = targetObject.findById(1);
        assertNotNull(car);
        targetObject.delete(car);

        //Then
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_car;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
    }

    @Test
    @SneakyThrows
    public void deleteId() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DaoImplTest.class.getResourceAsStream("DaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        targetObject.delete(1);

        //Then
        ResultSet rs = testMysqlJdbcDataSource
                .getConnection()
                .createStatement()
                .executeQuery("select count(*) from t_car;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DaoImplTest.class.getResourceAsStream("DaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Car car = targetObject.findById(1);

        //Then
        assertEquals("volkswagen polo", car.getNameCar());
    }
}