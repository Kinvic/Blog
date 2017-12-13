package dao;


import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DBConnection implements IUserDao{

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public User getById(Integer id) throws SQLException {
        String sql = "SELECT id, username, password FROM users WHERE id = ?";
        User user = null;
        ResultSet resultSet;
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        }
        resultSet.close();
        return user;
    }

    @Override
    public boolean create(User user) throws SQLException {

        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Creation user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return true;
    }

    @Override
    public User update(User entity) throws SQLException {
        User user = entity;
        String sql = "UPDATE users SET password=? WHERE id=?";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        }
        return user;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public User getByUsername(String username) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?";
        try(PreparedStatement preparedStatement = connectDB().prepareStatement(sql)){
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(username);
                user.setPassword(resultSet.getString("password"));
            }
            resultSet.close();
        }
        return user;
    }
}
