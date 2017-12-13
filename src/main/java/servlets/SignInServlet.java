package servlets;


import entity.User;
import service.CookieUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/signIn.jsp").forward(req, resp);
        CookieUtils.deleteCookies(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new UserService().signIn(login, password);
        CookieUtils.addCookies(resp, user.getId());
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
