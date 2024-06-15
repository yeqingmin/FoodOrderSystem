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


    public int getOrderMerchantIdByOrderId(int orderId);

    public ArrayList<Integer> calculateMonthlyOrderFrequencyChanges(int userId);
    //每月点餐频率

    public ArrayList<Integer> calculateWeeklyOrderFrequencyChanges(int userId);

    public int modifyOrderOnlineOrOffline(int orderId,int isOnline);

    //下面三个方法是各个商店用户特征分布

    public ArrayList<Integer> GenderConsumptionDistribution(int merchantId);

    public ArrayList<Integer> AgeConsumptionDistribution(int merchantId);

    public ArrayList<Integer> RoleConsumptionDistribution(int merchantId);

    //下面三个方法是各个菜品的用户分布特征
    public ArrayList<Integer> dishGenderConsumptionDistribution(int dishId);

    public ArrayList<Integer> dishAgeConsumptionDistribution(int dishId);

    public ArrayList<Integer> dishRoleConsumptionDistribution(int dishId);

    //下面三个方法是各个性别，年龄段，角色在一个商店的评价分布和打分情况
    //返回男生点评数量，女生点评数量，男生平均打分，女生平均打分
    public ArrayList<Integer> getGenderGroupEvaluationPatterns(int merchantId);

    //同上，先返回点评数量，再返回平局评分
    public ArrayList<Integer> getAgeGroupEvaluationPatterns(int merchantId);

    public ArrayList<Integer> getRoleGroupEvaluationPatterns(int merchantId);

}
