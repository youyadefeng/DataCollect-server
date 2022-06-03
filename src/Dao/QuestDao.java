package Dao;
import Dao.Bean.Option;
import Dao.Bean.Quest;
import Dao.Bean.SubQuestion;

import java.sql.SQLException;
import java.util.List;

public interface QuestDao {
    List<Quest> GetAllQuest() throws SQLException;
    List<Quest> GetQuestNotDo(int userid) throws SQLException;
    void UserDoQuest(int userid, int questid) throws SQLException;
    List<Quest> GetQuestNotHaving(int userid) throws SQLException;
    List<Quest> GetQuestHaving(int userid) throws SQLException;
    void UserBuyQuest(int userid, int questid) throws SQLException;
    List<SubQuestion> GetQuestion(int questid) throws SQLException;
    List<Option> GetOptions(int questid) throws SQLException;
}
