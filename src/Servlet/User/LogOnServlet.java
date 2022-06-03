package Servlet.User;

import Dao.Bean.Quest;
import Dao.QuestDaoImpl;
import Dao.UserDao;
import Dao.UserDaoImp;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class LogOnServlet extends HttpServlet {
    public void init() throws ServletException
    {

    }
    //request header: "username", "password"
    //response header: "logon" (1 success, 0 fail), "userid"
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        UserDaoImp userDaoImp = new UserDaoImp();
        boolean res = false;
        int userid = -1;
        try {
            //根据是否附加header来进行判断
            //if(request.getHeader("UserId") == null)
            if (!request.getHeader("username").isEmpty() && !request.getHeader("password").isEmpty())
            {
                res = userDaoImp.LogOn(request.getHeader("username"), request.getHeader("password"));
                userid = userDaoImp.GetUserId(request.getHeader("username"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (res)
        {
            response.setIntHeader("logon", 1);
            response.setIntHeader("userid", userid);
        }
        else
        {
            response.setIntHeader("logon",0);
            response.setIntHeader("userid",-1);
        }

    }
}
