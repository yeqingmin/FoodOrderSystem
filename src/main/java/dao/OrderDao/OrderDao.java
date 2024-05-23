package dao.OrderDao;

import pojo.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {
    int addOrder(Connection connection, Order order) throws Exception;

    List<Order> getOrdersByUserId(Connection connection, int userId) throws Exception;

    List<Order> getUserOrderHistory(Connection connection, int userId) throws Exception;
}
