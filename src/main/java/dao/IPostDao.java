package dao;

import entity.Post;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IPostDao extends IDAO<Post, Integer> {
    List<Post> getAll(int count, int offset)throws SQLException;
    List<Post> getUserPosts(Integer id, int limit, int offset) throws  SQLException;
    List<Post> getBySubstring(String substring) throws SQLException;
    Integer countPosts() throws SQLException;
    Integer countUserPosts(Integer id) throws SQLException;
}
