package Servlet.Quest;

import Dao.Bean.Quest;
import Dao.QuestDaoImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class UserBuyQuestServlet extends HttpServlet {
    public void init() throws ServletException
    {

    }

    //request header:"userid","questid"
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        QuestDaoImpl daoImpl = new QuestDaoImpl();
        int userid = request.getIntHeader("userid");
        int questid = request.getIntHeader("questid");
        try {
            daoImpl.UserBuyQuest(userid, questid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
