package servlet.Order;

import com.mysql.cj.util.StringUtils;
import dao.OrderDetailDao.OrderDetailDao;
import dao.OrderDetailDao.OrderDetailDaoImpl;
import pojo.*;
import service.Book.BookService;
import service.Dish.DishService;
import service.Dish.DishServiceImpl;
import service.Merchant.MerchantService;
import service.Merchant.MerchantServiceImpl;
import service.Message.MessageService;
import service.Message.MessageServiceImpl;
import service.Order.OrderService;
import service.Order.OrderServiceImpl;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class OrderServlet extends HttpServlet {
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
        } else if (method != null && method.equals("orderView")) {
            this.orderView(request, response);
        } else if (method != null && method.equals("orderDetail")) {
            this.orderDetail(request, response);
        } else if (method != null && method.equals("bookBegin")) {
            this.bookBegin(request, response);
        } else if (method != null && method.equals("book")) {
            this.book(request, response);
        }
    }

    /**
     * 向book表插入数据,同时发送一条book请求
     *
     * @param request
     * @param response
     */
    private void book(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String merchantId = request.getParameter("merchantId");
        Integer userId = Session.getCurrentId(request);
        System.out.println("merchantId："+merchantId);


        //给book插入数据
        String bookStartTime = request.getParameter("startTime");
        String bookEndTime = request.getParameter("endTime");
        System.out.println(bookStartTime);
        System.out.println(bookEndTime);

        BookService bookService = new BookService();

        Book book = new Book();
        book.setMerchantId(Integer.parseInt(merchantId));
        book.setUserId(userId);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime startTime = LocalDateTime.parse(bookStartTime, formatter);
        LocalDateTime endTime = LocalDateTime.parse(bookEndTime, formatter);
        Date startDate = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
        book.setBookStartTime(startDate);
        book.setBookEndTime(endDate);

        int bookId = bookService.addBook(book);

        //同时发送一条预订消息
        MessageService messageService = new MessageServiceImpl();
        MerchantService merchantService=new MerchantServiceImpl();
        //先查出对应的商家信息
        Merchant merchant=merchantService.getMerchantById(Integer.parseInt(merchantId));
        String message="您在"+merchant.getMerchantAddr()+"的"+merchant.getMerchantName()+"预订已成功！";
        messageService.addBookMessage(userId,bookId,message);

        request.getRequestDispatcher("merchant/merchantUserView.jsp").forward(request, response);
    }

    private void bookBegin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String merchantId = request.getParameter("merchantId");
        System.out.println("bookBegin merchantId:"+merchantId);
        request.setAttribute("merchantId", merchantId);
        request.getRequestDispatcher("user/bookPage.jsp").forward(request, response);
    }

    /**
     * 当前方法和orderView类似，但是不发系统消息
     *
     * @param request
     * @param response
     */
    private void orderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String orderId = request.getParameter("orderId");

        //新建orderService对象
        OrderService orderService = new OrderServiceImpl();
        DishService dishService = new DishServiceImpl();
        String url = "user/orderView.jsp";
        //还需要设置merchant的Id
        //先根据orderId获取到当前的order然后再获得order中的merchantId写评论


        //获取当前的orderId然后查出orderDetail中对应orderId的菜品id显示在上面，整个网页显示的是点菜的菜名，菜品数量，和订单创建时间
        queryOrderDetails(request, response, orderId, orderService, dishService, url);
    }

    private void queryOrderDetails(HttpServletRequest request, HttpServletResponse response, String orderId, OrderService orderService, DishService dishService, String url) throws ServletException, IOException {
        ArrayList<OrderDetail> details = orderService.getDetailsByOrderId(Integer.parseInt(orderId));
        ArrayList<Dish> orderedDishes = new ArrayList<>();
        float sumPrice = 0;
        Dish prevDish = null;
        for (OrderDetail detail : details) {
            int dishId = detail.getDishId();
            Dish dish = dishService.getDishById(dishId);
            sumPrice += dish.getDishPrice();
            dish.setTotalCount(dishService.countDishQuantity(dishId, Integer.parseInt(orderId)));
            if (prevDish != null && prevDish.getDishName().equals(dish.getDishName())) {
                continue;
            } else {
                orderedDishes.add(dish);
            }
            prevDish = dish;
        }
        request.setAttribute("dishList", orderedDishes);
        request.setAttribute("dishSumPrice", sumPrice);

        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * 当前方法发送一条下单成功的系统消息，并且跳转到订单详细查看的页面
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void orderView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer userId = Session.getCurrentId(request);

        String orderId = request.getParameter("orderId");
        String merchantId = request.getParameter("merchantId");


        //新建orderService对象
        OrderService orderService = new OrderServiceImpl();
        MessageService messageService = new MessageServiceImpl();
        MerchantService merchantService = new MerchantServiceImpl();
        DishService dishService = new DishServiceImpl();
        if (!StringUtils.isNullOrEmpty(merchantId)) {
            //根据orderId新建一条订单消息,先给merchantService让他查出merchantName和merchantAddr拼接消息
            Merchant merchant = merchantService.getMerchantById(Integer.parseInt(merchantId));
            String orderMessage = "您在" + merchant.getMerchantAddr() + "的" + merchant.getMerchantName() + "下的订单号为" + orderId + "的订单已下单成功";
            messageService.addOrderMessage(userId, Integer.parseInt(orderId), orderMessage);
        }

        //获取当前的orderId然后查出orderDetail中对应orderId的菜品id显示在上面，整个网页显示的是点菜的菜名，菜品数量，和订单创建时间
        queryOrderDetails(request, response, orderId, orderService, dishService, "user/orderDetailView.jsp");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Session.getCurrentId(request);
//        OrderService orderService=new OrderServiceImpl();
//        ArrayList<Order> orderList=orderService.getOrderListByUserId(userId);
//        request.setAttribute("orderList",orderList);
        String pageIndex = request.getParameter("pageIndex");

        OrderService orderService = new OrderServiceImpl();

        //第一次走页面一定是第一页,页面大小固定的
        ArrayList<Order> orderList = null;
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
        int totalCount = orderService.getOrderTotalCountById(userId);

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

        orderList = orderService.getOrderListByUserId(userId, currentPageNo, pageSize);
        request.setAttribute("orderList", orderList);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        request.getRequestDispatcher("user/orderList.jsp").forward(request, response);
    }
}
