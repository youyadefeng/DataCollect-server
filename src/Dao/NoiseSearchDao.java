package Dao;

import Dao.Bean.NoiseSearch;

import java.sql.SQLException;
import java.util.List;

public interface NoiseSearchDao {
    List<NoiseSearch> GetNoiseSearchDatas() throws SQLException;
    void InsertData(NoiseSearch data) throws SQLException;
    int GetDataNumber() throws SQLException;
}
