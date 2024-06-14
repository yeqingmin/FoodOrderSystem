package service.Order;

import pojo.Order;
import pojo.OrderDetail;

import java.sql.Connection;
import java.util.ArrayList;

public interface OrderService {
    public int addOrder(Order order);

    public ArrayList<Order> getOrderListByUserId(Integer userId,int currentPageNo, int pageSize);
    public int getOrderTotalCountById(int userId);
    public int getOnlineNumberByDishId(int dishId);
    public int getOfflineNumberByDishId(int dishId);
    public int getBiggestBuyer(int dishId);
    public int getWeeklyOnlineNumber(int dishId);
    public int getWeeklyOfflineNumber(int dishId);
    public int getMonthlyOnlineNumber(int dishId);
    public int getMonthlyOfflineNumber(int dishId);
    public int getYearlyOnlineNumber(int dishId);
    public int getYearlyOfflineNumber(int dishId);
    public ArrayList<Integer> getLoyalBuyers(int merchantId);
    public int getLoyalBuyersDishNumber(int userId ,int dishId);

    public ArrayList<OrderDetail> getDetailsByOrderId(int orderId);

    public String getOrderMerchantIdByOrderId(int orderId);
}
