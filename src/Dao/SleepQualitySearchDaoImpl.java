package Dao;

import Dao.Bean.NoiseSearch;
import Dao.Bean.SleepQualitySearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SleepQualitySearchDaoImpl implements SleepQualitySearchDao {

    @Override
    public List<SleepQualitySearch> GetSleepQualityData() throws SQLException {
        List<SleepQualitySearch> sleepQualitySearchList = new ArrayList<>();
        Connection connection = BaseDao.getConnection();
        String sql = "select * from sleep_quality_search";
        PreparedStatement stat = connection.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            SleepQualitySearch sleepQualitySearch = new SleepQualitySearch(rs.getInt("sleep_enough"),
                    rs.getInt("sleep_time"), rs.getInt("sleep_difficulty"));
            sleepQualitySearchList.add(sleepQualitySearch);
        }
        BaseDao.closeAll(connection, stat, rs);
        return sleepQualitySearchList;
    }

    @Override
    public void InsertSleepQualityData(SleepQualitySearch data) throws SQLException {
        Connection connection = BaseDao.getConnection();
        String sql = "insert into sleep_quality_search(sleep_enough,sleep_time,sleep_difficulty) value(?,?,?)";
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setInt(1, data.getSleepEnough());
        stat.setInt(2, data.getSleepTime());
        stat.setInt(3,data.getSleepDifficulty());
        stat.executeUpdate();
        BaseDao.closeAll(connection, stat, null);
    }

    @Override
    public int GetDataNumber() throws SQLException {
        int dataNumber = 0;
        Connection connection = BaseDao.getConnection();
        String sql = "select count(*) from sleep_quality_search";
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
