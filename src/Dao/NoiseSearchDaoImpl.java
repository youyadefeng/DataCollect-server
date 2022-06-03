package Dao;

import Dao.Bean.NoiseSearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoiseSearchDaoImpl implements NoiseSearchDao{
    @Override
    public List<NoiseSearch> GetNoiseSearchDatas() throws SQLException {
        List<NoiseSearch> noiseSearchList = new ArrayList<>();
        Connection connection = BaseDao.getConnection();
        String sql = "select * from noise_info_search";
        PreparedStatement stat = connection.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            NoiseSearch noiseSearch = new NoiseSearch(rs.getInt("noise_db"));
            noiseSearchList.add(noiseSearch);
        }
        BaseDao.closeAll(connection, stat, rs);
        return noiseSearchList;
    }

    @Override
    public void InsertData(NoiseSearch data) throws SQLException {
        Connection connection = BaseDao.getConnection();
        String sql = "insert into noise_info_search(noise_db) value(?)";
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setInt(1, data.getNoiseDb());
        stat.executeUpdate();

        BaseDao.closeAll(connection, stat, null);
    }

    @Override
    public int GetDataNumber() throws SQLException {
        int dataNumber = 0;
        Connection connection = BaseDao.getConnection();
        String sql = "select count(*) from noise_info_search";
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
