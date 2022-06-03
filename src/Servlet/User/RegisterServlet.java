package Servlet.User;

import Dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    public void init() throws ServletException
    {

    }
    //request header: "username", "password"
    //response header: "register" (1 success, 0 fail)
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        UserDaoImp userDaoImp = new UserDaoImp();
        boolean res = false;
        try {
            //根据是否附加header来进行判断
            //if(request.getHeader("UserId") == null)
            if (!request.getHeader("username").isEmpty() && !request.getHeader("password").isEmpty())
            {
                res = userDaoImp.Register(request.getHeader("username"), request.getHeader("password"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (res)
            response.setIntHeader("register", 1);
        else
            response.setIntHeader("register",0);
    }
}
