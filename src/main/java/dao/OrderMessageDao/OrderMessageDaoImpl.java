package dao.OrderMessageDao;

import dao.BaseDao;
import pojo.BookMessage;
import pojo.OrderMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderMessageDaoImpl implements OrderMessageDao{
    public int add(Connection connection, OrderMessage orderMessage) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO `ordermessage` (userId, orderStatusMessage, orderId) VALUES (?, ?, ?)";
            Object[] params ={orderMessage.getUserId(),orderMessage.getOrderStatusMessage(),orderMessage.getOrderId()};
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
            String sql="select * from `ordermessage` where userId=?";
            Object[] params ={userId};
            rs=BaseDao.execute(connection,preparedStatement,rs,sql,params);
            while(rs.next()){
                OrderMessage orderMessage=new OrderMessage();
                orderMessage.setOrderId(rs.getInt("orderId"));
                orderMessage.setMessageId(rs.getInt("messageId"));
                orderMessage.setUserId(rs.getInt("userId"));
                orderMessage.setOrderStatusMessage(rs.getString("orderStatusMessage"));
                orderMessage.setCreateTime(rs.getDate("createTime"));
                orderMessageList.add(orderMessage);
            }
            BaseDao.closeResource(connection,preparedStatement,rs);
        }
        return orderMessageList;
    }

    @Override
    public ArrayList<OrderMessage> getOrderMessageByUserId(Connection connection, int userId, int currentPageNo, int pageSize) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<OrderMessage> orderMessageList = new ArrayList<>();
        if (connection != null) {
            String sql="select * from `ordermessage` where userId= ?  limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {userId, currentPageNo,pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                OrderMessage orderMessage=new OrderMessage();
                orderMessage.setOrderStatusMessage(rs.getString("orderStatusMessage"));
                orderMessage.setCreateTime(rs.getDate("createTime"));
                orderMessage.setOrderId(rs.getInt("orderId"));
                orderMessageList.add(orderMessage);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return orderMessageList;
    }
    public int getOrderMessageCountByUserId(Connection connection,int userId) throws SQLException {
        int count=0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from `ordermessage` where userId=?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                count = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }
}
