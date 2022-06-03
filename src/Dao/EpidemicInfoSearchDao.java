package Dao;

import Dao.Bean.EpidemicInfoSearch;

import java.sql.SQLException;
import java.util.List;

public interface EpidemicInfoSearchDao {
    List<EpidemicInfoSearch> GetEpidemicInfoData() throws SQLException;
    void InsertEpidemicInfoData(EpidemicInfoSearch data) throws SQLException;
    int GetDataNumber() throws SQLException;
}
