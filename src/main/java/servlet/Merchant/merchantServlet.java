package servlet.Merchant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;
import pojo.*;
import service.Dish.DishService;
import service.Dish.DishServiceImpl;
import service.Merchant.MerchantService;
import service.Merchant.MerchantServiceImpl;
import service.Order.OrderService;
import service.Order.OrderServiceImpl;
import service.User.UserService;
import service.User.UserServiceImpl;
import utils.Constants;
import utils.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


/*
    如何根据session获取当前用户/商户的id
    HttpSession session = request.getSession();
        System.out.println("session "+session);
        if (session != null) {
            // 从session中获取Constants.USER_SESSION属性
            Login userSession = (Login) session.getAttribute(Constants.USER_SESSION);

            // 检查userSession是否为null，然后根据需要处理
            if (userSession != null) {
                // 处理userSession对象
                // 例如，将其转发到JSP页面
                request.setAttribute("userId", userSession.getCorrespondingID());
                System.out.println("userId:"+userSession.getCorrespondingID());
            }
        }
 */
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
        }else if (method != null && method.equals("view")) {
            this.getMerchantById(request, response, "merchant/merchantView.jsp");
        }else if(method != null && method.equals("adminManage")){
            this.adminManage(request, response, "admin/adminToMerchantList.jsp");
        }else if(method!=null && method.equals("userView")){
            this.getMerchantById(request,response,"merchant/merchantUserView.jsp");
        }else if(method!=null && method.equals("queryJSON")){
            this.queryJSON(request, response);
        }else if(method!=null && method.equals("queryMenu")){
            this.queryMenu(request,response,"merchant/merchantMenuViewToUser.jsp");
        }else if(method!=null && method.equals("createOrderAndListMenu")){
            this.createOrderAndListMenu(request,response,"user/orderPage.jsp");
        }

    }

    private void createOrderAndListMenu(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {

        //调用orderDao根据session得到userId，根据前端Parameter发送名字为orderMerchantId参数的值，获取当前创建时间，然后新建order，并返回新建的order的id
        int orderId = 0;
        Integer userId=Session.getCurrentId(request);

        //然后根据前端传参获取merchantId
        String merchantId = request.getParameter("merchantId");
        System.out.println("merchantId: " + merchantId);

        //新建orderService对象
        OrderService orderService = new OrderServiceImpl();
        if (!StringUtils.isNullOrEmpty(merchantId)) {
            //新建一个Order对象
            Order order = new Order();
            order.setMerchantId(Integer.parseInt(merchantId));
            order.setUserId(userId);
            orderId = orderService.addOrder(order);
            System.out.println("New orderId = "+orderId);
            //设置转发请求的参数orderId，以方便选择指定菜品向orderDetail表里面增加新的数据的时候对应正确的orderId
            request.setAttribute("orderId",orderId);
        }

        //以上全部搞完之后还要设置dishList，获取到当前merchantId对应的dishList设置在转发的请求中
        ArrayList<Dish> dishList=null;
        if(!StringUtils.isNullOrEmpty(merchantId)){
            DishService dishService=new DishServiceImpl();
            dishList= dishService.getDishByMerchantId(Integer.parseInt(merchantId));
            request.setAttribute("dishList",dishList);
        }

        request.getRequestDispatcher(url).forward(request,response);
    }

    /**
     * 该方法用于根据对应的merchantId来查询菜单
     * @param request
     * @param response
     */
    private void queryMenu(HttpServletRequest request, HttpServletResponse response,String url) throws ServletException, IOException {
        String merchantId=request.getParameter("merchantId");
        ArrayList<Dish> dishList=null;
        if(!StringUtils.isNullOrEmpty(merchantId)){
            DishService dishService=new DishServiceImpl();
            dishList= dishService.getDishByMerchantId(Integer.parseInt(merchantId));
            request.setAttribute("dishList",dishList);
        }
        request.getRequestDispatcher(url).forward(request,response);
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
        //查出所有的merchant
        MerchantService merchantService=new MerchantServiceImpl();
        ArrayList<Merchant> merchantList=merchantService.getAllMerchantList();
        request.setAttribute("merchantList",merchantList);
        request.getRequestDispatcher(url).forward(request,response);
    }

    private void getMerchantById(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        String id=request.getParameter("merchantId");
//        HttpSession session = request.getSession();
//        System.out.println("session "+session);
//        if (session != null) {
//            // 从session中获取Constants.USER_SESSION属性
//            Login userSession = (Login) session.getAttribute(Constants.USER_SESSION);
//
//            // 检查userSession是否为null，然后根据需要处理
//            if (userSession != null) {
//                // 处理userSession对象
//                // 例如，将其转发到JSP页面
//                request.setAttribute("userId", userSession.getCorrespondingID());
//                System.out.println("userId:"+userSession.getCorrespondingID());
//            }
//        }
        if(!StringUtils.isNullOrEmpty(id)){
            MerchantService merchantService=new MerchantServiceImpl();
            Merchant merchant=null;
            merchant=merchantService.getMerchantById(Integer.parseInt(id));
            request.setAttribute("merchant",merchant);
        }
        request.getRequestDispatcher(url).forward(request,response);
    }

    private void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        List<Provider> providerList = new ArrayList<Provider>();
//        ProviderService providerService = new ProviderServiceImpl();
//        providerList = providerService.getProviderList("","");
//        request.setAttribute("providerList", providerList);
        ArrayList<Merchant> merchantList = null;
        MerchantService merchantService = new MerchantServiceImpl();

//        String queryProductName = request.getParameter("queryProductName");
//        String queryProviderId = request.getParameter("queryProviderId");
//        String queryIsPayment = request.getParameter("queryIsPayment");
        //得到前端传来的数据
        String merchantName = request.getParameter("merchantName");
        if (StringUtils.isNullOrEmpty(merchantName)) {
            merchantName = "";
        }
        //根据前端传给我们的merchantName搜索对应的merchant列表
        merchantList=merchantService.getSimpleMerchantByName(merchantName);


        //这里设置的属性是后端数据库查出来的内容，然后转发给merchantList.jsp（下一个跳转页面的请求属性，让他来渲染我们查到的信息）
        request.setAttribute("merchantList", merchantList);

//        request.setAttribute("queryProviderId", queryProviderId);
//        request.setAttribute("queryIsPayment", queryIsPayment);
        request.getRequestDispatcher("user/merchantList.jsp").forward(request, response);

    }
}
