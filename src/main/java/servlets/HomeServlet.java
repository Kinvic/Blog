package servlets;

import dao.PostDAO;
import entity.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDAO postDAO = new PostDAO();
        List<Post> posts = new ArrayList<>();
        int page = 1;
        int postsPerPage = 4;
        int numberOfPages = 0;
        if(req.getParameter("page") != null){
            page = Integer.valueOf(req.getParameter("page"));
        }
        try{
            posts = postDAO.getAll(postsPerPage, (page-1)*postsPerPage);
            numberOfPages = (int)Math.ceil(postDAO.countPosts()*1.0/postsPerPage);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        req.setAttribute("posts", posts);
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("currentPage", page);
        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
