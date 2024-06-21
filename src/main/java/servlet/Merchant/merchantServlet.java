package servlet.Merchant;

import clojure.lang.IFn;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import pojo.*;
import service.Dish.DishService;
import service.Dish.DishServiceImpl;
import service.Favor.FavourService;
import service.Favor.FavourServiceImpl;
import service.Login.LoginService;
import service.Login.LoginServiceImpl;
import service.Merchant.MerchantService;
import service.Merchant.MerchantServiceImpl;
import service.Message.MessageService;
import service.Message.MessageServiceImpl;
import service.Order.OrderService;
import service.Order.OrderServiceImpl;
import service.Review.ReviewService;
import service.Review.ReviewServiceImpl;
import service.User.UserService;
import service.User.UserServiceImpl;
import utils.Constants;
import utils.PageSupport;
import utils.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class merchantServlet extends HttpServlet {
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
        } else if (method != null && method.equals("view")) {
            this.viewMerchantById(request, response, "merchant/merchantView.jsp");
        } else if (method != null && method.equals("adminManage")) {
            this.adminManage(request, response, "admin/adminToMerchantList.jsp");
        } else if (method != null && method.equals("userView")) {
            this.getMerchantById(request, response, "merchant/merchantUserView.jsp");
        } else if (method != null && method.equals("queryJSON")) {
            this.queryJSON(request, response);
        } else if (method != null && method.equals("queryMenu")) {
            this.queryMenu(request, response, "merchant/merchantMenuViewToUser.jsp");
        } else if (method != null && method.equals("createOrderAndListMenu")) {
            this.createOrderAndListMenu(request, response, "user/orderPage.jsp");
        } else if (method != null && method.equals("add")) {
            this.add(request, response);
        } else if (method != null && method.equals("modifyMerchant")) {
            this.getMerchantById(request, response, "admin/merchantmodify.jsp");
        } else if (method != null && method.equals("deleteMerchant")) {
            this.deleteMerchant(request, response);
        } else if (method != null && method.equals("modifyExecute")) {
            this.modifyMerchant(request, response);
        } else if (method != null && method.equals("review")) {
            this.reviewMerchant(request, response);
        } else if (method != null && method.equals("reviewMerchantBegin")) {
            this.getMerchantById(request, response, "merchant/merchantReview.jsp");
        } else if (method != null && method.equals("favorMerchant")) {
            this.favorMerchant(request, response);
        } else if (method != null && method.equals("queryReview")) {
            this.queryReview(request, response);
        } else if(method !=null && method.equals("analysis")){
            this.analysis(request,response);
        }else if(method!=null && method.equals("loyalUser")){
            this.getLoyalUsers(request,response);
        }else if(method!=null&&method.equals("getGenderDistribution")){
            this.getGenderDistribution(request,response);
        }else if(method!=null&&method.equals("getAgeDistribution")){
            this.getAgeDistribution(request,response);
        }else if(method!=null&&method.equals("getRoleDistribution")){
            this.getRoleDistribution(request,response);
        }else if(method!=null&&method.equals("customerAnalysis")){
            this.customerAnalysis(request,response);
        }

    }

    private void customerAnalysis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("merchant/customerAnalysis.jsp").forward(request, response);
    }

    private void getGenderDistribution(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应的内容类型和字符集
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //根据session获取当前merchant的id
        Integer merchantId=Session.getCurrentId(request);
        // 获取用户身份的分布数据
        OrderService orderService=new OrderServiceImpl();
        ArrayList<Integer> genderDistribution=orderService.GenderConsumptionDistribution(merchantId);

        // 使用Gson将数据转换为JSON格式并返回
        String jsonResponse = new Gson().toJson(genderDistribution);
        response.getWriter().write(jsonResponse);
    }

    private void getAgeDistribution(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应的内容类型和字符集
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 获取年龄分布数据
        //根据session获取当前merchant的id
        Integer merchantId=Session.getCurrentId(request);
        // 获取用户年龄的分布数据
        OrderService orderService=new OrderServiceImpl();
        ArrayList<Integer> ageDistribution=orderService.AgeConsumptionDistribution(merchantId);

        // 使用Gson将数据转换为JSON格式并返回
        String jsonResponse = new Gson().toJson(ageDistribution);
        response.getWriter().write(jsonResponse);
    }

    private void getRoleDistribution(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应的内容类型和字符集
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //根据session获取当前merchant的id
        Integer merchantId=Session.getCurrentId(request);
        // 获取用户身份的分布数据
        OrderService orderService=new OrderServiceImpl();
        ArrayList<Integer> roleDistribution=orderService.RoleConsumptionDistribution(merchantId);

        // 使用Gson将数据转换为JSON格式并返回
        String jsonResponse = new Gson().toJson(roleDistribution);
        response.getWriter().write(jsonResponse);
    }

    /**
     * 获取当前商户的忠实用户
     * @param request
     * @param response
     */
    private void getLoyalUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int merchantId=Session.getCurrentId(request);
        OrderService orderService=new OrderServiceImpl();
        UserService userService=new UserServiceImpl();
        ArrayList<Integer> userListId=orderService.getLoyalBuyers(merchantId);
        ArrayList<User> users=new ArrayList<>();
        for(int id:userListId){
            User user=userService.getUserById(id);
            users.add(user);
        }
        request.setAttribute("userList",users);
        request.getRequestDispatcher("merchant/loyalUsers.jsp").forward(request, response);
    }

    /**
     * 该方法用于显示数据分析页面,需要根据dishId计算线上和线下的销量，然后需要得到购买其最多的用户
     * @param request
     * @param response
     */
    private void analysis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据merchantId获取当前merchant的菜单
        int merchantId=Session.getCurrentId(request);
        String pageIndex = request.getParameter("pageIndex");

        MerchantService merchantService=new MerchantServiceImpl();
        DishService dishService=new DishServiceImpl();             //获取菜单和菜品总数
        OrderService orderService=new OrderServiceImpl();         //获取线上线下销量和购买最多的用户

        ArrayList<Dish> menu = null;
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
        int totalCount = dishService.getDishTotalCountByMerchantId(merchantId);
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

        menu = dishService.getDishWithSalesAndUserListByMerchantId(merchantId,currentPageNo,pageSize);
        request.setAttribute("dishList", menu);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);

        request.getRequestDispatcher("merchant/dishAnalysis.jsp").forward(request, response);
    }

    private void queryReview(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String merchantId = request.getParameter("merchantId");
        String pageIndex = request.getParameter("pageIndex");
        ReviewService reviewService = new ReviewServiceImpl();
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
        int totalCount = reviewService.getUMReviewTotalCountByMerchantId(Integer.parseInt(merchantId));
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
        ArrayList<UMReview> umReviewList = null;
        umReviewList = reviewService.getUMReviewListByMerchantId(Integer.parseInt(merchantId), currentPageNo, pageSize);
        request.setAttribute("merchantId", merchantId);
        request.setAttribute("umReviewList", umReviewList);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        request.getRequestDispatcher("merchant/merchantReviewView.jsp").forward(request, response);
    }

    private void favorMerchant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String merchantId = request.getParameter("merchantId");
        //根据session获取userId
        Integer userId = Session.getCurrentId(request);

        FavourService favourService = new FavourServiceImpl();
        favourService.favouriteMerchant(userId, Integer.parseInt(merchantId));

        response.setContentType("application/json");
        PrintWriter out = null;
        out = response.getWriter();
        out.write("{\"success\": " + true + "}");
    }

    private void reviewMerchant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应类型和编码
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 从请求中获取参数
        String merchantId = request.getParameter("merchantId");
        String merchantComment = request.getParameter("reviewText");
        String merchantRating = request.getParameter("rating");

        // 假设userId是从登录信息中获取的
        Integer userId = Session.getCurrentId(request); // 你需要实现这个方法来获取当前登录用户的ID

        // 使用UDReviewService添加评论
        ReviewService reviewService = new ReviewServiceImpl();
        int result = reviewService.reviewMerchant(userId, Integer.parseInt(merchantId), Integer.parseInt(merchantRating), merchantComment);


        // 构建响应结果
        JSONObject jsonResponse = new JSONObject();
        if (result != 0) {
            jsonResponse.put("status", "success");
            jsonResponse.put("message", "评价成功！");
        } else {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "评价失败！");
        }

        // 输出响应结果
        response.getWriter().print(jsonResponse.toString());
    }

    private void viewMerchantById(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        Integer id = Session.getCurrentId(request);
        MerchantService merchantService = new MerchantServiceImpl();
        Merchant merchant = null;
        merchant = merchantService.getMerchantById(id);
        System.out.println("merchantId:" + id);
        request.setAttribute("merchant", merchant);
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void deleteMerchant(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("merchantId");
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
            MerchantService merchantService = new MerchantServiceImpl();
            if (merchantService.deleteMerchantById(delId)) {
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

    private void modifyMerchant(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String merchantId = request.getParameter("merchantId");
        String merchantName = request.getParameter("merchantName");
        String merchantAddr = request.getParameter("merchantAddr");

        Merchant merchant = new Merchant();
        merchant.setMerchantId(Integer.parseInt(merchantId));
        merchant.setMerchantAddr(merchantAddr);
        merchant.setMerchantName(merchantName);
        MerchantService merchantService = new MerchantServiceImpl();
        if (merchantService.modifyMerchantById(merchant) != 0) {
            response.sendRedirect(request.getContextPath() + "/jsp/merchant?method=adminManage");
        } else {
            request.getRequestDispatcher("admin/merchantmodify.jsp").forward(request, response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("add()================");
        String merchantName = request.getParameter("merchantName");
        String merchantPassword = request.getParameter("merchantPassword");
        String merchantAddr = request.getParameter("merchantAddr");

        //创建两个Service
        LoginService loginService = new LoginServiceImpl();
        MerchantService merchantService = new MerchantServiceImpl();

        //新建一个Login对象
        //新建一个merchant对象
        Login merchantLogin = new Login();
        merchantLogin.setRole("merchant");
        merchantLogin.setName(merchantName);
        merchantLogin.setPassword(merchantPassword);
        Merchant merchant = new Merchant();
        merchant.setMerchantName(merchantName);
        merchant.setMerchantAddr(merchantAddr);

        //先插入一条新的merchant数据，然后获取到当前新建的merchantId，然后新建login对象
        int newMerchantId = merchantService.addMerchant(merchant);
        //设置login对应的merchantId
        merchantLogin.setCorrespondingID(newMerchantId);

        //给login表插入数据
        loginService.addLogin(merchantLogin);

        //然后重定向到不同页面
        response.sendRedirect(request.getContextPath() + "/jsp/merchant?method=adminManage");
        //request.getRequestDispatcher("admin/adminToMerchantList.jsp").forward(request, response);

    }


    private void createOrderAndListMenu(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {

        //调用orderDao根据session得到userId，根据前端Parameter发送名字为orderMerchantId参数的值，获取当前创建时间，然后新建order，并返回新建的order的id
        int orderId = 0;
        Integer userId = Session.getCurrentId(request);

        //然后根据前端传参获取merchantId
        String merchantId = request.getParameter("merchantId");

        //新建orderService对象
        OrderService orderService = new OrderServiceImpl();
//        MessageService messageService=new MessageServiceImpl();
//        MerchantService merchantService=new MerchantServiceImpl();
        if (!StringUtils.isNullOrEmpty(merchantId)) {
            //新建一个Order对象
            Order order = new Order();
            order.setMerchantId(Integer.parseInt(merchantId));
            order.setUserId(userId);
            orderId = orderService.addOrder(order);
            System.out.println("New orderId = " + orderId);
            //设置转发请求的参数orderId，以方便选择指定菜品向orderDetail表里面增加新的数据的时候对应正确的orderId
            request.setAttribute("orderId", orderId);
//            //根据orderId新建一条订单消息,先给merchantService让他查出merchantName和merchantAddr拼接消息
//            Merchant merchant=merchantService.getMerchantById(Integer.parseInt(merchantId));
//            String orderMessage="您在"+merchant.getMerchantAddr()+"的"+merchant.getMerchantName()+"下的订单号为"+orderId+"的订单已下单成功";
//            messageService.addOrderMessage(userId,orderId,orderMessage);
        }

        //以上全部搞完之后还要设置dishList，获取到当前merchantId对应的dishList设置在转发的请求中
        ArrayList<Dish> dishList = null;
        if (!StringUtils.isNullOrEmpty(merchantId)) {
            DishService dishService = new DishServiceImpl();
            dishList = dishService.getDishByMerchantId(Integer.parseInt(merchantId));
            request.setAttribute("dishList", dishList);
        }
        request.setAttribute("merchantId", merchantId);
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * 该方法用于根据对应的merchantId来查询菜单
     *
     * @param request
     * @param response
     */
    private void queryMenu(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        String merchantId = request.getParameter("merchantId");
        ArrayList<Dish> dishList = null;
        if (!StringUtils.isNullOrEmpty(merchantId)) {
            DishService dishService = new DishServiceImpl();
            dishList = dishService.getDishByMerchantId(Integer.parseInt(merchantId));
            request.setAttribute("dishList", dishList);
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    public void queryJSON(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Merchant> merchantList = null;
        MerchantService merchantService = new MerchantServiceImpl();
        // 得到前端传来的数据
        String merchantName = request.getParameter("merchantName");
        if (StringUtils.isNullOrEmpty(merchantName)) {
            merchantName = "";
        }
        // 根据前端传给我们的merchantName搜索对应的merchant列表
        merchantList = merchantService.getSimpleMerchantByName(merchantName);

        // 将查询结果转换为JSON格式的字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = null;
        try {
            jsonResponse = objectMapper.writeValueAsString(merchantList);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }

        // 设置响应的内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 将JSON字符串写入响应体
        try {
            response.getWriter().write(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void adminManage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        //以下是参考代码
        //查询用户列表

        //获取分页查询下标
        String pageIndex = request.getParameter("pageIndex");

        MerchantService merchantService = new MerchantServiceImpl();

        //第一次走页面一定是第一页,页面大小固定的
        ArrayList<Merchant> merchantList = null;
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
        int totalCount = merchantService.getMerchantTotalCount();

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

        merchantList = merchantService.getAllMerchantList(currentPageNo, pageSize);
        request.setAttribute("merchantList", merchantList);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void getMerchantById(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        String id = request.getParameter("merchantId");
        if (!StringUtils.isNullOrEmpty(id)) {
            MerchantService merchantService = new MerchantServiceImpl();
            Merchant merchant = null;
            merchant = merchantService.getMerchantById(Integer.parseInt(id));
            request.setAttribute("merchant", merchant);
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Merchant> merchantList = null;
        MerchantService merchantService = new MerchantServiceImpl();


        //得到前端传来的数据
        String merchantName = request.getParameter("merchantName");
        if (StringUtils.isNullOrEmpty(merchantName)) {
            merchantName = "";
        }
        //根据前端传给我们的merchantName搜索对应的merchant列表
        merchantList = merchantService.getSimpleMerchantByName(merchantName);


        //这里设置的属性是后端数据库查出来的内容，然后转发给merchantList.jsp（下一个跳转页面的请求属性，让他来渲染我们查到的信息）
        request.setAttribute("merchantList", merchantList);

        request.getRequestDispatcher("user/merchantList.jsp").forward(request, response);

    }
}
