package dao;

import entity.Post;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends DBConnection implements IPostDao{

    @Override
    public List<Post> getAll(int count, int offset) throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT posts.*, users.username, users.password, users.id AS creator FROM posts JOIN users ON posts.creator = users.id ORDER BY creationdate DESC LIMIT ? OFFSET ?";

        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                User user = new User();
                user.setId(resultSet.getInt("creator"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                post.setCreator(user);
                post.setCreationDate(resultSet.getTimestamp("creationDate").toLocalDateTime());
                post.setPosted(resultSet.getBoolean("isposted"));
                posts.add(post);
            }
            resultSet.close();
        }
        return posts;
    }

    @Override
    public Post update(Post entity) throws SQLException {
        Post post = entity;
        String sql = "UPDATE posts SET title=?, content=?, isposted=? WHERE id=?";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setBoolean(3, post.isPosted());
            preparedStatement.setInt(4, post.getId());
            preparedStatement.executeUpdate();
        }
        return post;
    }

    @Override
    public Post getById(Integer id) throws SQLException {
        Post post = new Post();
        String sql = "SELECT posts.*, users.username, users.password, users.id AS creator FROM posts JOIN users ON posts.creator = users.id WHERE posts.id = ?";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                post.setId(id);
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                User user = new User();
                user.setId(resultSet.getInt("creator"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                post.setCreator(user);
                post.setCreationDate(resultSet.getTimestamp("creationDate").toLocalDateTime());
                post.setPosted(resultSet.getBoolean("isposted"));
                post.setImage(resultSet.getString("image"));
            }
            resultSet.close();
        }

        return post;

    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM posts WHERE id = ?";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
        return true;
    }

    @Override
    public boolean create(Post post) throws SQLException {
        String sql = "INSERT INTO posts(title, content, creator, creationdate, isposted, image) VALUES(?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setInt(3, post.getCreator().getId());
            Timestamp timestamp = Timestamp.valueOf(post.getCreationDate());
            preparedStatement.setTimestamp(4, timestamp);
            preparedStatement.setBoolean(5, post.isPosted());
            preparedStatement.setString(6, post.getImage());
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Creation post failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating post failed, no ID obtained.");
                }
            }
        }
        return true;
    }

    @Override
    public List<Post> getUserPosts(Integer id, int limit, int offset) throws SQLException{
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT posts.*, users.username, users.password, users.id AS creator FROM posts JOIN users ON posts.creator = users.id WHERE creator = ? ORDER BY creationdate DESC LIMIT ? OFFSET ?";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                User user = new User();
                user.setId(resultSet.getInt("creator"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                post.setCreator(user);
                post.setCreationDate(resultSet.getTimestamp("creationDate").toLocalDateTime());
                post.setPosted(resultSet.getBoolean("isposted"));
                posts.add(post);
            }
            resultSet.close();
        }
        return posts;
    }

    @Override
    public List<Post> getBySubstring(String substring) throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT posts.*, users.username, users.password, users.id AS creator FROM posts JOIN users ON posts.creator = users.id WHERE LOWER(title) LIKE LOWER(?) OR LOWER(content) LIKE LOWER(?)";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setString(1, "%" + substring + "%");
            preparedStatement.setString(2, "%" + substring + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                User user = new User();
                user.setId(resultSet.getInt("creator"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                post.setCreator(user);
                post.setCreationDate(resultSet.getTimestamp("creationDate").toLocalDateTime());
                post.setPosted(resultSet.getBoolean("isposted"));
                posts.add(post);
            }
            resultSet.close();
        }
        return posts;
    }

    @Override
    public Integer countPosts() throws SQLException {
        Integer count = 0;
        String sql = "SELECT COUNT(id) FROM posts";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        }
        return count;
    }

    @Override
    public Integer countUserPosts(Integer id) throws SQLException {
        Integer count = 0;
        String sql = "SELECT COUNT(id) FROM posts WHERE creator = ?";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        }
        return count;
    }


}
