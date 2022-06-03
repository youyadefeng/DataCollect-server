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

public class QuestHavingServlet extends HttpServlet {
    public void init() throws ServletException
    {

    }

    //request header:"userid"
    //response body: list<Quest>  (json)
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        QuestDaoImpl daoImpl = new QuestDaoImpl();
        int userid = request.getIntHeader("userid");
        List<Quest> datas = null;
        try {
            datas = daoImpl.GetQuestHaving(userid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(datas);
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
    }
}
