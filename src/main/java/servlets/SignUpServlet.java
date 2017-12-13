package servlets;

import entity.User;
import service.CookieUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/signUp.jsp").forward(req,resp);
        CookieUtils.deleteCookies(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try{
            User user = new UserService().signUp(login, password);
            CookieUtils.addCookies(resp, user.getId());
        }
        catch (SQLException e){
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "?error=duplicated");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}