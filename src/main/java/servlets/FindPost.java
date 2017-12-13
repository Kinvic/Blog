package servlets;

import entity.Post;
import service.PostService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/findpost")
public class FindPost extends HttpServlet {
        PostService postService = new PostService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        List<Post> posts = null;
        try{
            posts = postService.findPosts(search);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        req.setAttribute("posts", posts);
        req.setAttribute("query", search);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }
}
