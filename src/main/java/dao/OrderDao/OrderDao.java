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
}
