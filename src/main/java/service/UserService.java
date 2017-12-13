package service;

import dao.UserDAO;
import entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import java.sql.SQLException;

public class UserService implements IUserService{
    private UserDAO userDAO = new UserDAO();

    @Override
    public User signIn(String login, String password) {
        if (StringUtils.isAnyBlank(login, password)){
            return null;
        }
        User user;
        try{
            user = userDAO.getByUsername(login);
            if (login.equals(user.getUsername()) && password.equals(user.getPassword())){
                return user;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User signUp(String login, String password) throws SQLException {
        if ((login.trim().isEmpty() || login.equals(null)) || (password.trim().isEmpty() || password.equals(null))){
            throw new NullPointerException();
        }
        UserDAO userDAO = new UserDAO();
        if(userDAO.getByUsername(login) != null){
            throw new SQLException("This login is already used");
        }
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        new UserDAO().create(user);
        return user;
    }

    @Override
    public void editPassword(String oldPass, String newPass, Integer id) throws SQLException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getById(id);
        if(oldPass.equals(user.getPassword())){
            user.setPassword(newPass);
        }
    }
}
