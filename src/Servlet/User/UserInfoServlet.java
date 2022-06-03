package Servlet.User;

import Dao.Bean.User;
import Dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserInfoServlet extends HttpServlet {
    public void init() throws ServletException
    {

    }
    // request header: "userid"
    // response header: "score", "username", "userid"
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int id = request.getIntHeader("userid");
        User user = null;
        UserDaoImp userDaoImp = new UserDaoImp();
        try {
            user = userDaoImp.GetUserInformation(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(user != null)
        {
            response.setIntHeader("score", user.getScore());
            response.setHeader("username",user.getUserName());
            response.setIntHeader("userid",user.getUserid());
        }
    }
}
