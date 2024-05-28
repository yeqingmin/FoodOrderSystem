package dao.OrderMessageDao;

import dao.BaseDao;
import pojo.BookMessage;
import pojo.OrderMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderMessageDaoImpl implements OrderMessageDao{
    public int add(Connection connection, OrderMessage orderMessage) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO ordermessage (messageId, userId, orderStatusMessage, orderId) VALUES (?, ?, ?, ?,)";
            Object[] params ={orderMessage.getMessageId(),orderMessage.getUserId(),orderMessage.getOrderStatusMessage(),orderMessage.getOrderId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
    public ArrayList<OrderMessage> getOrderMessage(Connection connection, int userId) throws Exception{
        PreparedStatement preparedStatement=null;
        ArrayList<OrderMessage> orderMessageList = new ArrayList<>();
        ResultSet rs=null;
        if(null != connection){
            String sql="select * from ordermessage where userId=?";
            Object[] params ={userId};
            rs=BaseDao.execute(connection,preparedStatement,rs,sql,params);
            while(rs.next()){
                OrderMessage orderMessage=new OrderMessage();
                orderMessage.setOrderId(rs.getInt("orderId"));
                orderMessage.setMessageId(rs.getInt("messageId"));
                orderMessage.setUserId(rs.getInt("userId"));
                orderMessage.setOrderStatusMessage(rs.getString("orderStatusMessage"));
                orderMessageList.add(orderMessage);
            }
            BaseDao.closeResource(connection,preparedStatement,rs);
        }
        return orderMessageList;
    }
}
