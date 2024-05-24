package servlet.Merchant;

import com.mysql.cj.util.StringUtils;
import pojo.Merchant;
import service.Merchant.MerchantService;
import service.Merchant.MerchantServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class merchantServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		/*String totalPrice = request.getParameter("totalPrice");
		//23.234   45
		BigDecimal totalPriceBigDecimal =
				//设置规则，小数点保留两位，多出部分，ROUND_DOWN 舍弃
				//ROUND_HALF_UP 四舍五入(5入) ROUND_UP 进位
				//ROUND_HALF_DOWN 四舍五入（5不入）
				new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN);*/

        String method = request.getParameter("method");
        if (method != null && method.equals("query")) {
            this.query(request, response);
        }
//        }else if(method != null && method.equals("add")){
//            this.add(request,response);
        else if (method != null && method.equals("view")) {
            this.getMerchantById(request, response, "merchant/merchantView.jsp");
        }else if(method != null && method.equals("adminManage")){
            this.adminManage(request, response, "admin/adminToMerchantList.jsp");
        }
//        }else if(method != null && method.equals("modify")){
//            this.getBillById(request,response,"billmodify.jsp");
//        }else if(method != null && method.equals("modifysave")){
//            this.modify(request,response);
//        }else if(method != null && method.equals("delbill")){
//            this.delBill(request,response);
//        }else if(method != null && method.equals("getproviderlist")){
//            this.getProviderlist(request,response);
//        }

    }

    private void adminManage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request,response);
    }

    private void getMerchantById(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request,response);
    }

    private void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        List<Provider> providerList = new ArrayList<Provider>();
//        ProviderService providerService = new ProviderServiceImpl();
//        providerList = providerService.getProviderList("","");
//        request.setAttribute("providerList", providerList);
        ArrayList<Merchant> merchantList = new ArrayList<Merchant>();
        MerchantService merchantService = new MerchantServiceImpl();

//        String queryProductName = request.getParameter("queryProductName");
//        String queryProviderId = request.getParameter("queryProviderId");
//        String queryIsPayment = request.getParameter("queryIsPayment");

        String merchantName = request.getParameter("merchantName");
        if (StringUtils.isNullOrEmpty(merchantName)) {
            merchantName = "";
        }


        request.setAttribute("merchantName", merchantName);
//        request.setAttribute("queryProviderId", queryProviderId);
//        request.setAttribute("queryIsPayment", queryIsPayment);
        request.getRequestDispatcher("user/merchantList.jsp").forward(request, response);

    }
}
