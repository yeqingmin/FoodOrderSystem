package dao.OrderMessageDao;

import pojo.OrderMessage;

import java.sql.Connection;
import java.util.ArrayList;

public interface OrderMessageDao {
    public int add(Connection connection, OrderMessage orderMessage)throws Exception;
    public ArrayList<OrderMessage> getOrderMessage(Connection connection, int userId) throws Exception;

}
