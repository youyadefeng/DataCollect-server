package Dao;

import Dao.Bean.Option;
import Dao.Bean.Quest;
import Dao.Bean.SubQuestion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestDaoImpl extends BaseDao implements QuestDao{

    @Override
    public List<Quest> GetAllQuest() throws SQLException {
        List<Quest> list = new ArrayList<Quest>();
        Connection connection = BaseDao.getConnection();
        String sql = "select * from quest_information";
        PreparedStatement stat = connection.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        while(rs.next())
        {
            Quest data = new Quest(
                    rs.getInt("questid"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getInt("reward"),
                    rs.getDouble("longitude"),
                    rs.getDouble("latitude"),
                    rs.getInt("visit_time")
            );
            list.add(data);
        }
        BaseDao.closeAll(connection, stat, rs);

        for (Quest quest: list) {
            switch (quest.getQuestId())
            {
                case 1:
                {
                    UserInfoSearchDaoImpl dao = new UserInfoSearchDaoImpl();
                    quest.setDataNumber(dao.GetDataNumber());
                    break;
                }
                case 2:
                {
                    EpidemicInfoSearchDaoImpl dao = new EpidemicInfoSearchDaoImpl();
                    quest.setDataNumber(dao.GetDataNumber());
                    break;
                }
                case 3:
                {
                    SleepQualitySearchDaoImpl dao = new SleepQualitySearchDaoImpl();
                    quest.setDataNumber(dao.GetDataNumber());
                    break;
                }
                case 4:
                {
                    NoiseSearchDaoImpl dao = new NoiseSearchDaoImpl();
                    quest.setDataNumber(dao.GetDataNumber());
                    break;
                }
            }
        }
        return list;
    }


    @Override
    public List<Quest> GetQuestNotDo(int userid) throws SQLException {
        List<Quest> questList = GetAllQuest();
        Connection connection = BaseDao.getConnection();
        String sql = "select * from user_do_quest where userid=?";
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setInt(1, userid);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            int questid = rs.getInt("questid");
            questList.removeIf(e->e.getQuestId() == questid);
        }
        BaseDao.closeAll(connection, stat, rs);
        return questList;
    }

    @Override
    public void UserDoQuest(int userid, int questid) throws SQLException {
        String sql = "insert into user_do_quest(userid, questid) value(?,?)";
        Connection connection = BaseDao.getConnection();
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setInt(1, userid);
        stat.setInt(2, questid);
        stat.executeUpdate();
        BaseDao.closeAll(connection, stat, null);
    }

    @Override
    public List<Quest> GetQuestNotHaving(int userid) throws SQLException {
        List<Quest> questList = GetAllQuest();
        String sql = "select * from user_having_quest where userid=?";
        Connection connection = BaseDao.getConnection();
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setInt(1, userid);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            int havingId = rs.getInt("questid");
            questList.removeIf(e->e.getQuestId() == havingId);
        }
        BaseDao.closeAll(connection, stat, rs);
        return questList;
    }

    @Override
    public List<Quest> GetQuestHaving(int userid) throws SQLException {
        List<Quest> questList = GetAllQuest();
        List<Quest> res = new ArrayList<>();
        String sql = "select * from user_having_quest where userid=?";
        Connection connection = BaseDao.getConnection();
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setInt(1, userid);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            int havingId = rs.getInt("questid");
            for (Quest quest:questList) {
                if (quest.getQuestId() == havingId)
                {
                    res.add(quest);
                    break;
                }
            }
        }
        BaseDao.closeAll(connection, stat, rs);
        return res;
    }

    @Override
    public void UserBuyQuest(int userid, int questid) throws SQLException {
        //需要在客户端执行积分判断逻辑，只有积分足够才允许调用这个购买逻辑
        //扣除相应积分  having表中添加相应表项
        Connection connection = BaseDao.getConnection();
        String sql1 = "select * from quest_information where questid=?";
        PreparedStatement stat1 = connection.prepareStatement(sql1);
        stat1.setInt(1, questid);
        ResultSet rs1 = stat1.executeQuery();
        int questPrice = 0;
        while (rs1.next())
        {
            questPrice = rs1.getInt("price");
            break;
        }

        String sql2 = "select * from user where userid=?";
        PreparedStatement stat2 = connection.prepareStatement(sql2);
        stat2.setInt(1, userid);
        ResultSet rs2 = stat2.executeQuery();
        int userScore = 0;
        while (rs2.next())
        {
            userScore = rs2.getInt("score");
            break;
        }

        String sql3 = "update user set score=? where userid=?";
        PreparedStatement stat3 = connection.prepareStatement(sql3);
        stat3.setInt(1, userScore-questPrice);
        stat3.setInt(2, userid);
        stat3.executeUpdate();

        String sql4 = "insert into user_having_quest(userid,questid) value(?,?)";
        PreparedStatement stat4 = connection.prepareStatement(sql4);
        stat4.setInt(1, userid);
        stat4.setInt(2, questid);
        stat4.executeUpdate();

        BaseDao.closeAll(connection, stat1, rs1);
        stat2.close();
        stat3.close();
        stat4.close();
        rs2.close();
    }

    @Override
    public List<SubQuestion> GetQuestion(int questid) throws SQLException {
        List<SubQuestion> questionList = new ArrayList<>();
        Connection connection = BaseDao.getConnection();
        String sql = "select * from subquestion where questid=?";
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setInt(1, questid);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            SubQuestion subQuestion = new SubQuestion(rs.getInt("questionid"),
                    rs.getString("description"),SubQuestion.QuestionType.Option);
            int type = rs.getInt("question_type");
            if (type == 1)
                subQuestion.setType(SubQuestion.QuestionType.Input);
            questionList.add(subQuestion);
        }
        BaseDao.closeAll(connection, stat, rs);
        return questionList;
    }

    @Override
    public List<Option> GetOptions(int questid) throws SQLException {
        List<Option> optionList = new ArrayList<>();
        Connection connection = BaseDao.getConnection();
        //注意这里的app，迁移到服务器时需要改成服务器对应的数据库名字
        String sql = "select * from app.option where questionid in (select questionid from subquestion where questid=?)";
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.setInt(1, questid);
        ResultSet rs = stat.executeQuery();
        while (rs.next())
        {
            Option option = new Option(rs.getInt("optionid"),
                    rs.getInt("questionid"), rs.getString("description"));
            optionList.add(option);
        }
        BaseDao.closeAll(connection, stat, rs);
        return optionList;
    }
}
