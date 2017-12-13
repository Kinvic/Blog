package dao;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao extends IDAO<User, Integer> {
    List<User> getAll() throws SQLException;
    User getByUsername(String username) throws SQLException;
}
