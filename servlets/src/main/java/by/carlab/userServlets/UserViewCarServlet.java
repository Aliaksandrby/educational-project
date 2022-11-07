package by.carlab.userServlets;

import by.carlab.DAO.DaoImpl;
import by.carlab.pojo.CarInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/viewCar")
public class UserViewCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("carId");
        CarInfo carInfo = new DaoImpl().findById(Integer.parseInt(parameter));
        request.setAttribute("carInfo", carInfo);
        getServletContext().getRequestDispatcher("/jsp/userJsp/user_view_car.jsp").forward(request,response);
    }
}
