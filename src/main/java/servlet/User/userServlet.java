package servlet.User;

import com.mysql.cj.util.StringUtils;
import pojo.Login;
import pojo.User;
import service.Login.LoginService;
import service.Login.LoginServiceImpl;
import service.User.UserService;
import service.User.UserServiceImpl;
import utils.Constants;
import utils.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class userServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if(method != null && method.equals("query")) {
            this.query(request, response);
        }else if(method != null && method.equals("add")){
            this.add(request,response);
        }else if(method != null && method.equals("view")){
            this.getUserById(request,response,"user/userview.jsp");
        }else if(method != null && method.equals("adminManage")){
            this.adminManage(request,response,"admin/adminToUserList.jsp");
        }
    }

    private void adminManage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
       //得到整个用户的列表
        UserService userService=new UserServiceImpl();
        ArrayList<User> userList=userService.getAllUserList();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher(url).forward(request,response);
    }

    private void getUserById(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        //由session得到userId的信息
        Integer userId= Session.getCurrentId(request);
        //根据userId查到对应的user
        UserService userService=new UserServiceImpl();
        User user=userService.getUserById(userId);
        request.setAttribute("user",user);
        request.getRequestDispatcher(url).forward(request,response);


    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
    }

    private void query(HttpServletRequest request, HttpServletResponse response) {

    }
}
