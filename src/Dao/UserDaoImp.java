package Dao;

import Dao.Bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserDaoImp implements UserDao{

    @Override
    public boolean LogOn(String name, String password) throws SQLException {
        Connection connection = BaseDao.getConnection();
        String sql = "select * from user where name=?";
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setString(1, name);
        ResultSet rs = stat.executeQuery();

        while(rs.next())
        {
            System.out.println("get data");
            System.out.println(rs.getString("name"));
            System.out.println(rs.getString("password"));
            if (rs.getString("password").equals(password))
            {
                return true;
            }
        }
        BaseDao.closeAll(connection, stat, rs);
        return false;
    }

    @Override
    public boolean Register(String name, String password) throws SQLException {
        //Have a same name user before?
        Connection connection = BaseDao.getConnection();
        String sql1 = "select * from user where name=?";
        PreparedStatement stat = connection.prepareStatement(sql1);
        stat.setString(1, name);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            BaseDao.closeAll(connection, stat, rs);
            return false;
        }

        //insert into table
        String sql2 = "insert into user(name, password, score) value(?,?,10)";
        PreparedStatement stat2 = connection.prepareStatement(sql2);
        stat2.setString(1, name);
        stat2.setString(2, password);
        int i = stat2.executeUpdate();
        boolean res;
        if (i >= 1)
            res = true;
        else
            res = false;
        BaseDao.closeAll(connection, stat, rs);
        BaseDao.closeAll(connection, stat2, rs);
        return res;
    }

    @Override
    public int GetUserId(String name) throws SQLException {
        Connection connection = BaseDao.getConnection();
        String sql1 = "select * from user where name=?";
        PreparedStatement stat = connection.prepareStatement(sql1);
        stat.setString(1, name);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            return rs.getInt("userid");
        }
        BaseDao.closeAll(connection, stat, rs);
        return -1;
    }

    @Override
    public User GetUserInformation(int userid) throws SQLException {
        Connection connection = BaseDao.getConnection();
        String sql1 = "select * from user where userid=?";
        PreparedStatement stat = connection.prepareStatement(sql1);
        stat.setInt(1,userid);
        ResultSet rs = stat.executeQuery();
        User user = null;
        while (rs.next())
        {
            user = new User(rs.getInt("userid"), rs.getString("name"),
                    rs.getString("password"), rs.getInt("score"));
            break;
        }
        BaseDao.closeAll(connection, stat, rs);
        return user;
    }

    @Override
    public boolean SignIn(int userid) throws SQLException {
        boolean hasLastTime = false;
        boolean successFlag = false;
        String nowTime = LocalDate.now().toString();
        //检查时间 更新时间（签到）
        Connection connection = BaseDao.getConnection();
        String sql1 = "select * from sign_in where userid=?";
        PreparedStatement stat1 = connection.prepareStatement(sql1);
        stat1.setInt(1,userid);
        ResultSet rs1 = stat1.executeQuery();
        while(rs1.next())
        {
            hasLastTime = true;
            String lastTime = rs1.getString("last_time");
            if(!lastTime.equals(nowTime))
                successFlag = true;
            break;
        }

        if(hasLastTime)
        {
            //如果成功签到了才更新时间
            if(successFlag)
            {
                String sql2 = "update sign_in set last_time=? where userid=?";
                PreparedStatement stat2 = connection.prepareStatement(sql2);
                stat2.setString(1, nowTime);
                stat2.setInt(2, userid);
                stat2.executeUpdate();
                BaseDao.closeAll(connection, stat2, null);
            }
        }
        else
        {
            //如果之前没有记录，则插入一条记录，并且成功签到
            successFlag = true;
            String sql2 = "insert into sign_in(userid, last_time) value(?,?)";
            PreparedStatement stat2 = connection.prepareStatement(sql2);
            stat2.setInt(1,userid);
            stat2.setString(2,nowTime);
            stat2.executeUpdate();
            BaseDao.closeAll(connection, stat2, null);
        }
        BaseDao.closeAll(connection, stat1, rs1);
        return successFlag;
    }

    @Override
    public int AddScore(int userid, int score) throws SQLException {
        int beforeScore = 0;
        Connection connection = BaseDao.getConnection();
        String sql1 = "select * from user where userid=?";
        PreparedStatement stat = connection.prepareStatement(sql1);
        stat.setInt(1, userid);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            beforeScore = rs.getInt("score");
            break;
        }

        String sql2 = "update user set score=? where userid=?";
        PreparedStatement stat2 = connection.prepareStatement(sql2);
        stat2.setInt(1, score+beforeScore);
        stat2.setInt(2, userid);
        stat2.executeUpdate();

        BaseDao.closeAll(connection, stat, rs);
        stat2.close();
        return score + beforeScore;
    }
}
