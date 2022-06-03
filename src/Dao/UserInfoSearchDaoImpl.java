package Dao;

import Dao.Bean.UserInfoSearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoSearchDaoImpl implements UserInfoSearchDao{

    @Override
    public List<UserInfoSearch> GetUserInfoData() throws SQLException {
        List<UserInfoSearch> userInfoSearchList = new ArrayList<>();
        Connection connection = BaseDao.getConnection();
        String sql = "select * from user_info_search";
        PreparedStatement stat = connection.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            UserInfoSearch userInfoSearch = new UserInfoSearch(rs.getInt("sex"), rs.getInt("age"));
            userInfoSearchList.add(userInfoSearch);
        }
        BaseDao.closeAll(connection, stat, rs);
        return userInfoSearchList;
    }

    @Override
    public void InsertUserInfoData(UserInfoSearch data) throws SQLException {
        Connection connection = BaseDao.getConnection();
        String sql = "insert into user_info_search(sex, age) value(?,?)";
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setInt(1, data.getSex());
        stat.setInt(2, data.getAge());
        stat.executeUpdate();
        BaseDao.closeAll(connection, stat, null);
    }

    @Override
    public int GetDataNumber() throws SQLException {
        int dataNumber = 0;
        Connection connection = BaseDao.getConnection();
        String sql = "select count(*) from user_info_search";
        PreparedStatement stat = connection.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            dataNumber = rs.getInt(1);
            break;
        }
        BaseDao.closeAll(connection, stat, rs);
        return dataNumber;
    }
}
