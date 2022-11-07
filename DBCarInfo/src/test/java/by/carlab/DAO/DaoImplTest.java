package by.carlab.DAO;

import by.carlab.pojo.CarInfo;
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
        CarInfo carInfo = new CarInfo("bmw","x1","hatchback",
                "medium","black","electron",100.00,"");
        targetObject.create(carInfo);

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
        List<CarInfo> carInfoList = targetObject.readNotes();

        //Then
        assertEquals(1, carInfoList.size());
        assertEquals("1", carInfoList.get(0).getId().toString());
        assertEquals("audi", carInfoList.get(0).getBrand());
        assertEquals("a6 allroad", carInfoList.get(0).getFullName());
        assertEquals("sedan", carInfoList.get(0).getTypeBody());
        assertEquals("economy class", carInfoList.get(0).getClassAuto());
        assertEquals("red", carInfoList.get(0).getColor());
        assertEquals("1.9 TDI", carInfoList.get(0).getEngineDescription());
        assertEquals("40.55", Double.toString(carInfoList.get(0).getPrice()));
        assertEquals("1", carInfoList.get(0).getPathToImage());

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
        CarInfo carInfo = targetObject.findById(1);
        assertNotNull(carInfo);
        targetObject.delete(carInfo);

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
        CarInfo carInfo = targetObject.findById(1);

        //Then
        assertEquals("a6 allroad", carInfo.getFullName());
    }

    /*@Test
    @SneakyThrows
    public void readAll() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DaoImplTest.class.getResourceAsStream("DaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        List<CarInfo> carInfoList = targetObject.readAll();

        //Then
        assertEquals(1, carInfoList.size());
        assertEquals("1", carInfoList.get(0).getId().toString());
        assertEquals("audi", carInfoList.get(0).getBrand());
        assertEquals("a6 allroad", carInfoList.get(0).getFullName());
        assertEquals("sedan", carInfoList.get(0).getTypeBody());
        assertEquals("economy class", carInfoList.get(0).getClassAuto());
        assertEquals("red", carInfoList.get(0).getColor());
        assertEquals("1.9 TDI", carInfoList.get(0).getEngineDescription());
        assertEquals("40.55", Double.toString(carInfoList.get(0).getPrice()));
        assertEquals("1", carInfoList.get(0).getPathToImage());

        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }*/
}