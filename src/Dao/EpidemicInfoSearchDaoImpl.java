package Dao;

import Dao.Bean.EpidemicInfoSearch;
import Dao.Bean.NoiseSearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpidemicInfoSearchDaoImpl implements EpidemicInfoSearchDao{

    @Override
    public List<EpidemicInfoSearch> GetEpidemicInfoData() throws SQLException {
        List<EpidemicInfoSearch> epidemicInfoSearchList = new ArrayList<>();
        Connection connection = BaseDao.getConnection();
        String sql = "select * from epidemic_info_search";
        PreparedStatement stat = connection.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            EpidemicInfoSearch data = new EpidemicInfoSearch(rs.getInt("in_risk_area"),
                    rs.getInt("temperature"), rs.getInt("is_fever"), rs.getInt("is_contact"));
            epidemicInfoSearchList.add(data);
        }
        BaseDao.closeAll(connection, stat, rs);
        return epidemicInfoSearchList;
    }

    @Override
    public void InsertEpidemicInfoData(EpidemicInfoSearch data) throws SQLException {
        Connection connection = BaseDao.getConnection();
        String sql = "insert into epidemic_info_search(in_risk_area,temperature,is_fever,is_contact) value(?,?,?,?)";
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setInt(1, data.getInRiskArea());
        stat.setInt(2, data.getTemperature());
        stat.setInt(3, data.getIsFever());
        stat.setInt(4, data.getIsContact());
        stat.executeUpdate();
        BaseDao.closeAll(connection, stat, null);
    }

    @Override
    public int GetDataNumber() throws SQLException {
        int dataNumber = 0;
        Connection connection = BaseDao.getConnection();
        String sql = "select count(*) from epidemic_info_search";
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
