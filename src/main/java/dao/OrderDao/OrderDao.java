package dao.OrderDao;

import pojo.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderDao {
    int addOrder(Connection connection, Order order) throws Exception;

    public ArrayList<Order> getOrdersByUserId(Connection connection,int userId,int currentPageNo, int pageSize) throws Exception;

    List<Order> getUserOrderHistory(Connection connection, int userId) throws Exception;

    public int getOrderTotalCountByUserId(Connection connection,int userId) throws SQLException;

    public int getDishOnlineNumber(Connection connection,int dishId) throws SQLException;

    public int getDisOfflineNumber(Connection connection,int dishId) throws SQLException;

    public ArrayList<Integer> getLoyalBuyers(Connection connection,int merchantId) throws SQLException;

    //  忠实用户的菜品购买分布
    public int LoyalUserDIshOrderNumbers(Connection connection,int userId,int dishId) throws SQLException;

    public ArrayList<Integer> calculateMonthlyOrderFrequencyChanges(Connection connection,int userId) throws SQLException;
    //每月点餐频率

    public ArrayList<Integer> calculateWeeklyOrderFrequencyChanges(Connection connection,int userId) throws SQLException;

    public Order getOrderById(Connection connection,int orderId) throws SQLException;

    public int modifyOrderOnlineOrOffline(Connection connection,int orderId,int isOnline) throws Exception;



    public ArrayList<Integer> GenderConsumptionDistribution(Connection connection,int merchantId) throws Exception;

    public ArrayList<Integer> AgeConsumptionDistribution(Connection connection,int merchantId) throws Exception;

    public ArrayList<Integer> RoleConsumptionDistribution(Connection connection,int merchantId) throws Exception;



    public ArrayList<Integer> dishGenderConsumptionDistribution(Connection connection,int dishId) throws Exception;

    public ArrayList<Integer> dishAgeConsumptionDistribution(Connection connection,int dishId) throws Exception;

    public ArrayList<Integer> dishRoleConsumptionDistribution(Connection connection,int dishId) throws Exception;


    public ArrayList<Integer> getGenderGroupEvaluationPatterns(Connection connection, int merchantId) throws Exception;

    public ArrayList<Integer> getAgeGroupEvaluationPatterns(Connection connection, int merchantId) throws Exception;

    public ArrayList<Integer> getRoleGroupEvaluationPatterns(Connection connection, int merchantId) throws Exception;

    //用户近30天的每日各个时间段的活跃程度分析
    public ArrayList<Integer> getUserDailyActivityLevel(Connection connection, int userId) throws Exception;

    //同名商家中查找客流量最大的商户
    public int getMostVisitedMerchant(Connection connection , String merchantName) throws Exception;


}
