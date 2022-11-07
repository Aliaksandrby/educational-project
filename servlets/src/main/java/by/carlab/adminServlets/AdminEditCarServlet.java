package by.carlab.adminServlets;

import by.carlab.DAO.Dao;
import by.carlab.DAO.DaoImpl;
import by.carlab.pojo.CarInfo;

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
    private CarInfo carInfo;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carId = req.getParameter("carId");
        carInfo = carDao.findById(Integer.parseInt(carId));
        getServletContext().getRequestDispatcher("/jsp/adminJsp/admin_edit_car.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String brand = req.getParameter("brand");
        String fullName = req.getParameter("fullName");
        String typeBody = req.getParameter("typeBody");
        String classAuto = req.getParameter("classAuto");
        String color = req.getParameter("color");
        String engineDescription = req.getParameter("engineDescription");
        double price = Double.parseDouble(req.getParameter("price"));
        String pathToImage = req.getParameter("pathToImage");

        carInfo.setBrand(brand);
        carInfo.setFullName(fullName);
        carInfo.setTypeBody(typeBody);
        carInfo.setClassAuto(classAuto);
        carInfo.setColor(color);
        carInfo.setEngineDescription(engineDescription);
        carInfo.setPrice(price);
        carInfo.setPathToImage(pathToImage);

        carDao.update(carInfo);

        List<CarInfo> carInfoList = carDao.readNotes();

        req.setAttribute("carInfoList", carInfoList);
        getServletContext().getRequestDispatcher("/jsp/adminJsp/admin_view_cars.jsp").forward(req,resp);
    }
}
