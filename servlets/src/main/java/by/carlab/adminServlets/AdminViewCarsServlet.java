package by.carlab.adminServlets;

import by.carlab.DAO.DaoImpl;
import by.carlab.pojo.CarInfo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/adminViewCars")
public class AdminViewCarsServlet extends HttpServlet {
    private List<CarInfo> carInfoList;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        carInfoList = new DaoImpl().readNotes();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("carInfoList", carInfoList);
        getServletContext().getRequestDispatcher("/jsp/adminJsp/admin_view_cars.jsp").forward(request,response);
    }
}
