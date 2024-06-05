package dao.OrderMessageDao;

import pojo.BookMessage;
import pojo.OrderMessage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderMessageDao {
    public int add(Connection connection, OrderMessage orderMessage)throws Exception;
    public ArrayList<OrderMessage> getOrderMessage(Connection connection, int userId) throws Exception;
    public ArrayList<OrderMessage> getOrderMessageByUserId(Connection connection, int userId, int currentPageNo, int pageSize) throws Exception;
    public int getOrderMessageCountByUserId(Connection connection,int userId) throws SQLException;
}
