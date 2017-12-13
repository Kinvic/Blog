package servlets;

import dao.PostDAO;
import entity.Post;
import service.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/post")
public class PostSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = null;
        try{
            post = new PostDAO().getById(Integer.valueOf(req.getParameter("id")));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        req.setAttribute("post",post);
        req.setAttribute("img", post.getImage());
        req.setAttribute("userId", CookieUtils.getCookies(req));
        getServletContext().getRequestDispatcher("/post.jsp").forward(req, resp);
    }
}
