package servlets;

import dao.PostDAO;
import dao.UserDAO;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/editpost")
public class EditPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.setAttribute("post", new PostDAO().getById(Integer.valueOf(req.getParameter("id"))));
            getServletContext().getRequestDispatcher("/editpost.jsp").forward(req, resp);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String id = req.getParameter("id");
        boolean status;
        if (req.getParameter("status") == null){
            status = false;
        }
        else{
            status = true;
        }
        try{
            new PostService().editPost(title, content, status, id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/post?id="+req.getParameter("id"));
    }
}
