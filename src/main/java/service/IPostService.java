package service;

import entity.Post;

import java.sql.SQLException;
import java.util.List;

public interface IPostService {
    Post createPost(String title, String context, boolean status, Integer creator, String image) throws SQLException;
    void editPost(String title, String context, boolean status, String id) throws SQLException;
    void deletePost(Integer id) throws SQLException;
    List<Post> findPosts(String substring) throws SQLException;
}
