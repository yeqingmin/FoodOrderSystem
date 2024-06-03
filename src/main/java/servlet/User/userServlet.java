package servlet.User;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import pojo.Login;
import pojo.Merchant;
import pojo.User;
import service.Login.LoginService;
import service.Login.LoginServiceImpl;
import service.Merchant.MerchantService;
import service.Merchant.MerchantServiceImpl;
import service.User.UserService;
import service.User.UserServiceImpl;
import utils.Constants;
import utils.PageSupport;
import utils.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class userServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method != null && method.equals("query")) {
            this.query(request, response);
        } else if (method != null && method.equals("add")) {
            this.add(request, response);
        } else if (method != null && method.equals("view")) {
            this.getUserById(request, response, "user/userview.jsp");
        } else if (method != null && method.equals("adminManage")) {
            this.adminManage(request, response, "admin/adminToUserList.jsp");
        } else if (method != null && method.equals("modifyUser")) {
            this.getUserByIdAdmin(request, response, "admin/usermodify.jsp");
        } else if (method != null && method.equals("deleteUser")) {
            this.deleteUser(request, response);
        } else if (method != null && method.equals("modifyExecute")) {
            this.modifyUser(request, response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("userId");
        Integer delId = 0;
        try {
            delId = Integer.parseInt(id);
        } catch (Exception e) {
            // TODO: handle exception
            delId = 0;
        }
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (delId <= 0) {
            resultMap.put("deleteResult", "notexist");
        } else {
            UserService userService = new UserServiceImpl();
            if (userService.deleteUserById(delId) == 1) {
                resultMap.put("deleteResult", "true");
            } else {
                resultMap.put("deleteResult", "false");
            }
        }

        //把resultMap转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    private void modifyUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userGender = request.getParameter("userGender");

        User user = new User();
        user.setUserId(Integer.parseInt(id));
        user.setUserName(userName);
        user.setUserGender(userGender);
        user.setDelete(false);
        UserService userService = new UserServiceImpl();
        if (userService.modify(user) != 0) {
            response.sendRedirect(request.getContextPath() + "/jsp/user?method=adminManage");
        } else {
            request.getRequestDispatcher("admin/usermodify.jsp").forward(request, response);
        }
    }

    private void adminManage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        //得到整个用户的列表，实现分页查询

        //以下是参考代码
        //查询用户列表

        //获取分页查询下标
        String pageIndex = request.getParameter("pageIndex");

        UserService userService = new UserServiceImpl();

        //第一次走页面一定是第一页,页面大小固定的
        ArrayList<User> userList = null;
        //设置页面容量
        int pageSize = Constants.pageSize;
        //当前页码
        int currentPageNo = 1;

        System.out.println("query pageIndex--------- > " + pageIndex);

        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                response.sendRedirect("error.jsp");
            }
        }
        //获取总数
        int totalCount = userService.getUserTotalCount();

        //总页数
        PageSupport pages = new PageSupport();

        pages.setCurrentPageNo(currentPageNo);

        pages.setPageSize(pageSize);

        pages.setTotalCount(totalCount);

        int totalPageCount = pages.getTotalPageCount();

        //控制首页和尾页
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }

        userList = userService.getAllUserList(currentPageNo, pageSize);
        request.setAttribute("userList", userList);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void getUserById(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        //由session得到userId的信息
        Integer userId = Session.getCurrentId(request);
        //       String userId=request.getParameter("userId");
        //根据userId查到对应的user
        UserService userService = new UserServiceImpl();
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void getUserByIdAdmin(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        //根据userId查到对应的user
        UserService userService = new UserServiceImpl();
        User user = userService.getUserById(Integer.parseInt(userId));
        request.setAttribute("user", user);
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("add()================");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String userGender = request.getParameter("userGender");

        //创建两个Service
        LoginService loginService = new LoginServiceImpl();
        UserService userService = new UserServiceImpl();

        //新建一个Login对象
        //新建一个user对象
        Login userLogin = new Login();
        userLogin.setRole("user");
        userLogin.setName(userName);
        userLogin.setPassword(userPassword);
        User user = new User();
        user.setUserName(userName);
        user.setUserGender(userGender);


        //先插入一条新的user数据，然后获取到当前新建的userId，然后新建login对象
        int newUserId = userService.add(user);
        //设置login对应的userId
        userLogin.setCorrespondingID(newUserId);

        //给login表插入数据
        loginService.addLogin(userLogin);

        //然后重定向到不同页面
        response.sendRedirect(request.getContextPath() + "/jsp/user?method=adminManage");
        //request.getRequestDispatcher("/jsp/user?method=adminManage").forward(request, response);

    }

    private void query(HttpServletRequest request, HttpServletResponse response) {

    }
}
