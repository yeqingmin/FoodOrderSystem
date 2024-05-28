package servlet.Order;

import pojo.Order;
import service.Order.OrderService;
import service.Order.OrderServiceImpl;
import utils.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class OrderServlet extends HttpServlet {
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
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId= Session.getCurrentId(request);
        OrderService orderService=new OrderServiceImpl();
        ArrayList<Order> orderList=orderService.getOrderListByUserId(userId);
        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("user/orderList.jsp").forward(request, response);
    }
}
