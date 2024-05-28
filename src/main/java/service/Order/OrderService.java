package service.Order;

import pojo.Order;

import java.sql.Connection;
import java.util.ArrayList;

public interface OrderService {
    public int addOrder(Order order);

    public ArrayList<Order> getOrderListByUserId(Integer userId);
}
