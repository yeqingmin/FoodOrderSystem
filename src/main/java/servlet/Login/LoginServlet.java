package servlet.Login;

import pojo.Login;
import service.Login.LoginService;
import service.Login.LoginServiceImpl;
import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    //获取用户名和密码，从前端的标签内容
        String name=request.getParameter("userCode");
       // System.out.println("name"+name);
        String password=request.getParameter("userPassword");
        //System.out.println("password"+password);
        //调用Service方法，进行用户匹配
        LoginService loginService=new LoginServiceImpl();
        Login login=loginService.getLoginUser(name,password);

        //判断登录的逻辑
        if(null!=login){
            //若登录成功
            //放入Session
            request.getSession().setAttribute(Constants.USER_SESSION,login);
            response.sendRedirect("jsp/frame.jsp");
        }else{
            //若登录失败
            request.setAttribute("error", "用户名或密码不正确");
            //跳转页面回去，然后设置错误信息
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
