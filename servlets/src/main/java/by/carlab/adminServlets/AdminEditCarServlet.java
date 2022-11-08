package by.carlab.adminServlets;

import by.carlab.DAO.Dao;
import by.carlab.DAO.DaoImpl;
import by.carlab.pojo.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/edit.do")
public class AdminEditCarServlet extends HttpServlet {

    private final Dao carDao = new DaoImpl();
    private Car car;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carId = req.getParameter("carId");
        car = carDao.findById(Integer.parseInt(carId));
        getServletContext().getRequestDispatcher("/jsp/adminJsp/admin_edit_car.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nameCar = req.getParameter("nameCar");
        String typeOfBody = req.getParameter("typeOfBody");
        String typeEngine = req.getParameter("typeEngine");
        String typeTransmission = req.getParameter("typeTransmission");
        int yearOfIssue = Integer.parseInt(req.getParameter("yearOfIssue"));
        String image = req.getParameter("image");
        double price = Double.parseDouble(req.getParameter("price"));

        car.setNameCar(nameCar);
        car.setTypeOfBody(typeOfBody);
        car.setTypeEngine(typeEngine);
        car.setTypeTransmission(typeTransmission);
        car.setYearOfIssue(yearOfIssue);
        car.setImage(image);
        car.setPrice(price);

        carDao.update(car);

        List<Car> carList = carDao.readNotes();

        req.setAttribute("carList", carList);
        getServletContext().getRequestDispatcher("/jsp/adminJsp/admin_view_cars.jsp").forward(req,resp);
    }
}
