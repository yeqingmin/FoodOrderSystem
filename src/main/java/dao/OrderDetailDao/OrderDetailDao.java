package dao.OrderDetailDao;

import java.sql.Connection;

public interface OrderDetailDao {
    public int addDishToOrder(Connection connection,Integer dishId,Integer orderId) throws Exception;

    public int deleteDishFromOrder(Connection connection,Integer dishId,Integer orderId) throws Exception;

    public int countDishInOrderQuantities(Connection connection,Integer dishId,Integer orderId) throws Exception;

    public int biggestBuyerOfDish(Connection connection,int dishId) throws Exception;

    public int weeklyOnlineDishNumber(Connection connection,int dishId) throws Exception;

    public int weeklyOfflineDishNumber(Connection connection,int dishId) throws Exception;

    public int monthlyOfflineDishNumber(Connection connection,int dishId) throws Exception;

    public int monthlyOnlineDishNumber(Connection connection,int dishId) throws Exception;

    public int yearlyOfflineDishNumber(Connection connection,int dishId) throws Exception;

    public int yearlyOnlineDishNumber(Connection connection,int dishId) throws Exception;


}
