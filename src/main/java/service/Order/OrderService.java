package service.Order;

import pojo.Order;

import java.sql.Connection;

public interface OrderService {
    public int addOrder(Order order);
}
