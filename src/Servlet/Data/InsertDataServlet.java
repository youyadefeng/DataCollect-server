package Servlet.Data;

import Dao.*;
import Dao.Bean.EpidemicInfoSearch;
import Dao.Bean.NoiseSearch;
import Dao.Bean.SleepQualitySearch;
import Dao.Bean.UserInfoSearch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class InsertDataServlet extends HttpServlet {
    public void init() throws ServletException
    {

    }
    //request header: "questid", "data", "userid", "reward"
    //according to questid, transform http body to different type:
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int questid = request.getIntHeader("questid");
        int userid = request.getIntHeader("userid");
        int reward = request.getIntHeader("reward");

        Gson gson = new Gson();
        String jsonStr = request.getHeader("data");
        try {
            //insert data
            switch (questid)
            {
                case 1:
                {
                    UserInfoSearch userInfoSearch = gson.fromJson(jsonStr, new TypeToken<UserInfoSearch>(){}.getType());
                    UserInfoSearchDaoImpl dao = new UserInfoSearchDaoImpl();
                    dao.InsertUserInfoData(userInfoSearch);
                    break;
                }
                case 2:
                {
                    EpidemicInfoSearch epidemicInfoSearch = gson.fromJson(jsonStr, new TypeToken<EpidemicInfoSearch>(){}.getType());
                    EpidemicInfoSearchDaoImpl epidemicInfoSearchDao = new EpidemicInfoSearchDaoImpl();
                    epidemicInfoSearchDao.InsertEpidemicInfoData(epidemicInfoSearch);
                    break;
                }
                case 3:
                {
                    SleepQualitySearch sleepQualitySearch = gson.fromJson(jsonStr, new TypeToken<SleepQualitySearch>(){}.getType());
                    SleepQualitySearchDaoImpl dao = new SleepQualitySearchDaoImpl();
                    dao.InsertSleepQualityData(sleepQualitySearch);
                    break;
                }
                case 4:
                {
                    NoiseSearch noiseSearch = gson.fromJson(jsonStr, new TypeToken<NoiseSearch>(){}.getType());
                    NoiseSearchDaoImpl dao = new NoiseSearchDaoImpl();
                    dao.InsertData(noiseSearch);
                    break;
                }
            }

            // get reward
            UserDaoImp dao = new UserDaoImp();
            dao.AddScore(userid, reward);

            // hide quest ?
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
