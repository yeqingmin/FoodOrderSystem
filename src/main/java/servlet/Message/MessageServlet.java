package servlet.Message;

import pojo.BookMessage;
import pojo.OrderMessage;
import pojo.User;
import service.Message.MessageService;
import service.Message.MessageServiceImpl;
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

public class MessageServlet extends HttpServlet {
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
        }else if(method !=null&&method.equals("queryOrderMessage")){
            this.queryOrderMessage(request,response);
        }else if(method !=null&&method.equals("queryBookMessage")){
            this.queryBookMessage(request,response);
        }
    }

    private void queryBookMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageIndex = request.getParameter("pageIndex");
        int userId=Session.getCurrentId(request);
        MessageService messageService=new MessageServiceImpl();
        //第一次走页面一定是第一页,页面大小固定的
        ArrayList<BookMessage> bookMessageList=null;
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
        int totalCount = messageService.getBookMessageTotalCountByUserId(userId);
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
        bookMessageList=messageService.getBookMessageListByUserId(userId,currentPageNo,pageSize);
        request.setAttribute("bookMessageList",bookMessageList);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);


        request.getRequestDispatcher("user/bookMessage.jsp").forward(request, response);
    }

    private void queryOrderMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageIndex = request.getParameter("pageIndex");
        int userId=Session.getCurrentId(request);
        MessageService messageService=new MessageServiceImpl();
        //第一次走页面一定是第一页,页面大小固定的
        ArrayList<OrderMessage> OrderMessageList=null;
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
        int totalCount = messageService.getOrderMessageTotalCountByUserId(userId);
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
        OrderMessageList=messageService.getOrderMessageListByUserId(userId,currentPageNo,pageSize);
        request.setAttribute("orderMessageList",OrderMessageList);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);

        request.getRequestDispatcher("user/orderMessage.jsp").forward(request, response);
    }


    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId= Session.getCurrentId(request);
        request.setAttribute("userId",userId);
        request.getRequestDispatcher("user/systemMessage.jsp").forward(request, response);
    }
}
