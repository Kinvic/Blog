package service;

import dao.PostDAO;
import dao.UserDAO;
import entity.Post;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostService implements IPostService{
    private PostDAO postDAO = new PostDAO();

    @Override
    public Post createPost(String title, String content, boolean status, Integer creator, String image) throws SQLException {
        if(StringUtils.isAnyBlank(title, content)){
            throw new NullPointerException();
        }
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreator(new UserDAO().getById(creator));
        post.setCreationDate(LocalDateTime.now());
        post.setPosted(status);
        post.setImage(image);
        postDAO.create(post);
        return post;
    }

    @Override
    public void editPost(String title, String content, boolean status, String id) throws SQLException {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setPosted(status);
        post.setId(Integer.parseInt(id));
        postDAO.update(post);
    }

    @Override
    public void deletePost(Integer id) throws SQLException {
        postDAO.delete(id);
    }

    @Override
    public List<Post> findPosts(String substring) throws SQLException{
        return postDAO.getBySubstring(substring);
    }
}
