package servlet.Dish;

import com.mysql.cj.util.StringUtils;
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
        }
    }

    private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //商户管理菜单；对对应merchantId而select出来的dish进行增删改查
        request.getRequestDispatcher("merchant/dishManage.jsp").forward(request, response);
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Merchant> dishList=new ArrayList<Merchant>();
        DishService dishService=new DishServiceImpl();

//       String merchantName=request.getParameter("merchantName");
//        if(StringUtils.isNullOrEmpty(merchantName)){
//            merchantName = "";
//        }
//
//
//
//        request.setAttribute("merchantName", merchantName);
       request.getRequestDispatcher("user/dishList.jsp").forward(request, response);
    }
}
