package servlets;

import entity.Post;
import service.CookieUtils;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

@WebServlet("/createPost")
@MultipartConfig
public class CreatePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/createPost.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Post post = null;
        boolean status;
        if (req.getParameter("status") == null){
            status = false;
        }
        else{
            status = true;
        }
        int userId = Integer.valueOf(CookieUtils.getCookies(req));

        String image = "images/default.jpg";
        Part filePart = req.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if(!fileName.equals("")) {
            String appPath = req.getServletContext().getRealPath("");
            filePart.write(appPath + "../images/" + fileName);
            image = "../images/" + fileName;
        }
        try{
            PostService postService = new PostService();
            post = postService.createPost(title, content, status, userId, image);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/post?id=" + post.getId());
    }
}
