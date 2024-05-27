package servlet.Dish;

import com.mysql.cj.util.StringUtils;
import pojo.Dish;
import pojo.Merchant;
import service.Dish.DishService;
import service.Dish.DishServiceImpl;
import service.Merchant.MerchantService;
import service.Merchant.MerchantServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class dishServlet extends HttpServlet {
    DishService dishService=new DishServiceImpl();
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
        }else if(method != null && method.equals("merchantManage")){
            this.manage(request,response);
        }else if(method != null && method.equals("userView")){
            this.getDishByIdToUser(request, response);
        }
    }

    private void getDishByIdToUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dishId=request.getParameter("dishId");
        if(!StringUtils.isNullOrEmpty(dishId)){
            Dish dish=dishService.getDishById(Integer.parseInt(dishId));
            request.setAttribute("dish",dish);
        }
        request.getRequestDispatcher("user/dishView.jsp").forward(request, response);
    }

    private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //商户管理菜单；对对应merchantId而select出来的dish进行增删改查
        request.getRequestDispatcher("merchant/dishManage.jsp").forward(request, response);
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DishService dishService=new DishServiceImpl();

//        //先接收dishName的参数
        String dishName=request.getParameter("dishName");
        //再接收到当前的merchantId
        String merchantId=request.getParameter("merchantAddr");
        if(!StringUtils.isNullOrEmpty(dishName)&&!StringUtils.isNullOrEmpty(merchantId)){
            //然后调用dishService中
            ArrayList<Dish> dishList=new ArrayList<>();
            Dish dish=dishService.getDishByNameAndMerchant(dishName,Integer.parseInt(merchantId));
            dishList.add(dish);
            //设置对应的请求参数
            request.setAttribute("dishList",dishList);
        }

       request.getRequestDispatcher("user/dishList.jsp").forward(request, response);
    }
}
