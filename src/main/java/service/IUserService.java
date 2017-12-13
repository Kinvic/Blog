package service;

import entity.User;

import java.sql.SQLException;

public interface IUserService {
    User signIn(String login, String password) throws SQLException;
    User signUp(String login, String password) throws SQLException;
    void editPassword(String oldPass, String newPass, Integer id) throws SQLException;
}
