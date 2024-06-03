package servlet.Dish;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import pojo.Dish;
import pojo.Merchant;
import pojo.UDReview;
import service.Dish.DishService;
import service.Dish.DishServiceImpl;
import service.Favor.FavourService;
import service.Favor.FavourServiceImpl;
import service.Merchant.MerchantService;
import service.Merchant.MerchantServiceImpl;
import service.Review.ReviewService;
import service.Review.ReviewServiceImpl;
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

public class dishServlet extends HttpServlet {
    DishService dishService = new DishServiceImpl();

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
        } else if (method != null && method.equals("merchantManage")) {
            this.manage(request, response);
        } else if (method != null && method.equals("userView")) {
            this.getDishByIdToUser(request, response);
        } else if (method != null && method.equals("addDishToOrder")) {
            this.addDishToOrder(request, response);
        } else if (method != null && method.equals("deleteDishFromOrder")) {
            this.deleteDishFromOrder(request, response);
        } else if (method != null && method.equals("modifyDish")) {
            this.getDishById(request, response, "merchant/dishmodify.jsp");
        } else if (method != null && method.equals("deleteDish")) {
            this.deleteDish(request, response);
        } else if (method != null && method.equals("modifyExecute")) {
            this.modifyDish(request, response);
        } else if (method != null && method.equals("favorDishFromOrder")) {
            this.favorDishFromOrder(request, response);
        } else if (method != null && method.equals("review")) {
            this.reviewDish(request, response);
        } else if (method != null && method.equals("reviewDishBegin")) {
            this.getDishById(request,response,"user/dishReview.jsp");
        }

    }

    private void reviewDish(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 设置响应类型和编码
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 从请求中获取参数
        String dishId = request.getParameter("dishId");
        String dishComment = request.getParameter("reviewText");
        String dishRating = request.getParameter("rating");

        // 假设userId是从登录信息中获取的
        Integer userId = Session.getCurrentId(request); // 你需要实现这个方法来获取当前登录用户的ID

        // 使用UDReviewService添加评论
        ReviewService reviewService = new ReviewServiceImpl();
        int result = reviewService.reviewDish(userId, Integer.parseInt(dishId), Integer.parseInt(dishRating), dishComment);


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

    private void favorDishFromOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dishId = request.getParameter("dishId");
        //根据session获取userId
        Integer userId = Session.getCurrentId(request);

        FavourService favourService = new FavourServiceImpl();
        favourService.favouriteDish(userId, Integer.parseInt(dishId));

        response.setContentType("application/json");
        PrintWriter out = null;
        out = response.getWriter();
        out.write("{\"success\": " + true + "}");
    }


    private void deleteDish(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("dishId");
        Integer delId = 0;
        try {
            delId = Integer.parseInt(id);
        } catch (Exception e) {
            delId = 0;
        }
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (delId <= 0) {
            resultMap.put("deleteResult", "notexist");
        } else {
            DishService dishService = new DishServiceImpl();
            if (dishService.deleteDishById(delId) != 0) {
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

    private void modifyDish(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String dishId = request.getParameter("dishId");
        String dishName = request.getParameter("dishName");
        String dishPrice = request.getParameter("dishPrice");
        String dishCategory = request.getParameter("dishCategory");
        String dishDescription = request.getParameter("dishDescription");
        String dishAllergens = request.getParameter("dishAllergens");
        String dishIngredients = request.getParameter("dishIngredients");
        String dishNutrition = request.getParameter("dishNutrition");

        Dish dish = new Dish();
        dish.setDishId(Integer.parseInt(dishId));
        dish.setDishName(dishName);
        dish.setDishPrice(Float.parseFloat(dishPrice));
        dish.setDishCategory(dishCategory);
        dish.setDishAllergens(dishAllergens);
        dish.setDishDescription(dishDescription);
        dish.setDishIngredients(dishIngredients);
        dish.setDishNutrition(dishNutrition);


        DishService dishService = new DishServiceImpl();
        if (dishService.modifyDishById(dish) != 0) {
            response.sendRedirect(request.getContextPath() + "/jsp/dish?method=merchantManage");
        } else {
            request.getRequestDispatcher("admin/dishmodify.jsp").forward(request, response);
        }
    }

    private void getDishById(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        String id = request.getParameter("dishId");
        if (!StringUtils.isNullOrEmpty(id)) {
            DishService dishService = new DishServiceImpl();
            Dish dish = null;
            dish = dishService.getDishById(Integer.parseInt(id));
            System.out.println(dish);
            request.setAttribute("dish", dish);
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * 当前方法
     *
     * @param request
     * @param response
     */
    private void deleteDishFromOrder(HttpServletRequest request, HttpServletResponse response) {
        int quantity = 0;

        //从前端获得dishId和orderId
        String dishId = request.getParameter("dishId");
        String orderId = request.getParameter("orderId");

        //todo 重要的逻辑：我们需要判断一下是否没得删了然后给前端传消息,调用OrderDetail的count方法得到quantity
        int oldquantity = dishService.countDishQuantity(Integer.parseInt(dishId), Integer.parseInt(orderId));
        System.out.println(oldquantity);
        if (oldquantity > 0) {

            if (!StringUtils.isNullOrEmpty(dishId) && !StringUtils.isNullOrEmpty(orderId)) {
                quantity = dishService.deleteDishFromOrder(Integer.parseInt(dishId), Integer.parseInt(orderId));
                System.out.println("quantity=============" + quantity);
            }
            //发送响应到前端（json响应数据)
            jsonToJSP(response, quantity, true);
        } else {
            jsonToJSP(response, 0, false);
        }
    }

    private void addDishToOrder(HttpServletRequest request, HttpServletResponse response) {
        int quantity = 0;

        //从前端获得dishId和orderId
        String dishId = request.getParameter("dishId");
        String orderId = request.getParameter("orderId");

        if (!StringUtils.isNullOrEmpty(dishId) && !StringUtils.isNullOrEmpty(orderId)) {
            quantity = dishService.addDishToOrder(Integer.parseInt(dishId), Integer.parseInt(orderId));
            System.out.println("quantity=============" + quantity);
        }

        //发送响应到前端（json响应数据)
        jsonToJSP(response, quantity, true);
    }

    private void jsonToJSP(HttpServletResponse response, int quantity, boolean isSucess) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write("{\"success\": " + isSucess + ", \"quantity\": " + quantity + "}");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private void getDishByIdToUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dishId = request.getParameter("dishId");
        if (!StringUtils.isNullOrEmpty(dishId)) {
            Dish dish = dishService.getDishById(Integer.parseInt(dishId));
            request.setAttribute("dish", dish);
        }
        request.getRequestDispatcher("user/dishView.jsp").forward(request, response);
    }

    private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //商户管理菜单；对对应merchantId而select出来的dish进行增删改查

        //获取分页查询下标

        String pageIndex = request.getParameter("pageIndex");

        //根据session获取到merchantid
        Integer merchantId = Session.getCurrentId(request);

        DishService dishService = new DishServiceImpl();

        //第一次走页面一定是第一页,页面大小固定的
        ArrayList<Dish> dishList = null;
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

        dishList = dishService.getDishListByMerchantId(merchantId, currentPageNo, pageSize);
        request.setAttribute("dishList", dishList);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        //request.getRequestDispatcher(url).forward(request, response);

        request.getRequestDispatcher("merchant/dishManage.jsp").forward(request, response);
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //实现分页查询
        DishService dishService = new DishServiceImpl();

//        //先接收dishName的参数
        String dishName = request.getParameter("dishName");
        //再接收到当前的merchantId
        String merchantId = request.getParameter("merchantAddr");
        if (!StringUtils.isNullOrEmpty(dishName) && !StringUtils.isNullOrEmpty(merchantId)) {
            //然后调用dishService中
            ArrayList<Dish> dishList = new ArrayList<>();
            Dish dish = dishService.getDishByNameAndMerchant(dishName, Integer.parseInt(merchantId));
            dishList.add(dish);
            //设置对应的请求参数
            request.setAttribute("dishList", dishList);
        }

        request.getRequestDispatcher("user/dishList.jsp").forward(request, response);
    }
}
