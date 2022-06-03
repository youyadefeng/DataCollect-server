package Dao;

import Dao.Bean.SleepQualitySearch;

import java.sql.SQLException;
import java.util.List;

public interface SleepQualitySearchDao {
    List<SleepQualitySearch> GetSleepQualityData() throws SQLException;
    void InsertSleepQualityData(SleepQualitySearch data) throws SQLException;
    int GetDataNumber() throws SQLException;
}
