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

@WebServlet("/delete.do")
public class AdminDeleteCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Dao carDao = new DaoImpl();
        String carId = req.getParameter("carId");
        CarInfo carInfo = carDao.findById(Integer.parseInt(carId));
        carDao.delete(carInfo);

        List<CarInfo> carInfoList = carDao.readNotes();

        req.setAttribute("carInfoList", carInfoList);
        getServletContext().getRequestDispatcher("/jsp/adminJsp/admin_view_cars.jsp").forward(req, resp);
    }
}
