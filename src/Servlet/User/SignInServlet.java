package Servlet.User;

import Dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignInServlet extends HttpServlet {
    final int SING_IN_SCORE = 10;
    public void init() throws ServletException
    {

    }
    //request header: "userid"
    //response header: "signin" (1 success, 0 fail), "nowscore"
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        UserDaoImp userDaoImp = new UserDaoImp();
        boolean res = false;
        int nowScore = 0;
        try {
            //根据是否附加header来进行判断
            //if(request.getHeader("UserId") == null)
            int userid = request.getIntHeader("userid");
            boolean isAddScore = userDaoImp.SignIn(userid);
            if (isAddScore)
                nowScore = userDaoImp.AddScore(userid, SING_IN_SCORE);
            res = isAddScore;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (res)
        {
            response.setIntHeader("signin", 1);
            response.setIntHeader("newscore", nowScore);
        }
        else
        {
            response.setIntHeader("signin",0);
        }
    }
}
