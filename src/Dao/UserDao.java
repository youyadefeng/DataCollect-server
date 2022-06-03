package Dao;

import Dao.Bean.User;

import java.sql.SQLException;

public interface UserDao {
    boolean LogOn(String name, String password) throws SQLException;
    boolean Register(String name, String password) throws SQLException;
    int GetUserId(String name) throws SQLException;
    User GetUserInformation(int userid) throws SQLException;
    boolean SignIn(int userid) throws SQLException;
    int AddScore(int userid, int score) throws  SQLException;
}
