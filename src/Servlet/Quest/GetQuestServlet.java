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

public class GetQuestServlet extends HttpServlet {
    public void init() throws ServletException
    {

    }

    //response body: list<Quest> (json)
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        QuestDaoImpl daoImpl = new QuestDaoImpl();
        List<Quest> datas = null;
        try {
            //根据是否附加header来进行判断
            //if(request.getHeader("UserId") == null)
            datas = daoImpl.GetAllQuest();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(datas);
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
    }
}
