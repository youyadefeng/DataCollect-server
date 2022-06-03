package Servlet.Data;

import Dao.*;
import Dao.Bean.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class GetDataServlet extends HttpServlet {
    public void init() throws ServletException
    {

    }
    //request header:"questid"
    //response body:  according to questid contain different list<type>
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String jsonStr = null;
        int questid = request.getIntHeader("questid");

        try {
            switch (questid)
            {
                case 1:
                {
                    UserInfoSearchDaoImpl dao = new UserInfoSearchDaoImpl();
                    List<UserInfoSearch> data = dao.GetUserInfoData();
                    jsonStr = gson.toJson(data);
                    break;
                }
                case 2:
                {
                    EpidemicInfoSearchDaoImpl dao = new EpidemicInfoSearchDaoImpl();
                    List<EpidemicInfoSearch> data = dao.GetEpidemicInfoData();
                    jsonStr = gson.toJson(data);
                    break;
                }
                case 3:
                {
                    SleepQualitySearchDaoImpl dao = new SleepQualitySearchDaoImpl();
                    List<SleepQualitySearch> data = dao.GetSleepQualityData();
                    jsonStr = gson.toJson(data);
                    break;
                }
                case 4:
                {
                    NoiseSearchDaoImpl dao = new NoiseSearchDaoImpl();
                    List<NoiseSearch> data = dao.GetNoiseSearchDatas();
                    jsonStr = gson.toJson(data);
                    break;
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
    }
}
