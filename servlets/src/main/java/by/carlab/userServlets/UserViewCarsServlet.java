package by.carlab.userServlets;

import by.carlab.DAO.DaoImpl;
import by.carlab.pojo.Car;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserViewCarsServlet extends HttpServlet {
    private List<Car> carList;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        carList = new DaoImpl().readNotes();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("carList", carList);
        getServletContext().getRequestDispatcher("/jsp/userJsp/user_view_cars.jsp").forward(request,response);
    }
}
