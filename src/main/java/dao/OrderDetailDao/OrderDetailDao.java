package dao.OrderDetailDao;

import java.sql.Connection;

public interface OrderDetailDao {
    public int addDishToOrder(Connection connection,Integer dishId,Integer orderId) throws Exception;
    public int deleteDishFromOrder(Connection connection,Integer dishId,Integer orderId) throws Exception;

    public int countDishInOrderQuantities(Connection connection,Integer dishId,Integer orderId) throws Exception;
}
