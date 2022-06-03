package Dao;

import Dao.Bean.UserInfoSearch;

import java.sql.SQLException;
import java.util.List;

public interface UserInfoSearchDao {
    List<UserInfoSearch> GetUserInfoData() throws SQLException;
    void InsertUserInfoData(UserInfoSearch data) throws SQLException;
    int GetDataNumber() throws SQLException;
}
