package servlet.User;

import com.mysql.cj.util.StringUtils;
import pojo.Login;
import pojo.User;
import service.Login.LoginService;
import service.Login.LoginServiceImpl;
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
       //得到整个用户的列表，实现分页查询

        // todo 以下是原代码
//        UserService userService=new UserServiceImpl();
//        ArrayList<User> userList=userService.getAllUserList();
//        request.setAttribute("userList",userList);
//        request.getRequestDispatcher(url).forward(request,response);

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

            if(pageIndex != null){
                try{
                    currentPageNo = Integer.valueOf(pageIndex);
                }catch(NumberFormatException e){
                    response.sendRedirect("error.jsp");
                }
            }
            //总数量（表）
            ArrayList<User> users=userService.getAllUserList();
            int totalCount=users.size();
            //总页数
            PageSupport pages=new PageSupport();

            pages.setCurrentPageNo(currentPageNo);

            pages.setPageSize(pageSize);

            pages.setTotalCount(totalCount);

            int totalPageCount = pages.getTotalPageCount();

            //控制首页和尾页
            if(currentPageNo < 1){
                currentPageNo = 1;
            }else if(currentPageNo > totalPageCount){
                currentPageNo = totalPageCount;
            }


//            userList = userService.getUserList(queryUserName,queryUserRole,currentPageNo, pageSize);
//            request.setAttribute("userList", userList);
//            List<Role> roleList = null;
//            RoleService roleService = new RoleServiceImpl();
//            roleList = roleService.getRoleList();
//            request.setAttribute("roleList", roleList);
//            request.setAttribute("queryUserName", queryUserName);
//            request.setAttribute("queryUserRole", queryUserRole);
//            request.setAttribute("totalPageCount", totalPageCount);
//            request.setAttribute("totalCount", totalCount);
//            request.setAttribute("currentPageNo", currentPageNo);
//            request.getRequestDispatcher("userlist.jsp").forward(request, response);

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
