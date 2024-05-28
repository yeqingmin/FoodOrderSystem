package dao.OrderDao;

import dao.BaseDao;
import pojo.Order;
import pojo.UDReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao{
    public int addOrder(Connection connection, Order order) throws Exception{
        PreparedStatement pstm = null;
        int affectedRows = 0;
        if(null != connection){
            String sql = "insert into `order` (userId, merchantId) values (?, ?)";
            Object[] params ={order.getUserId(),order.getMerchantId()};
            affectedRows = BaseDao.executeAdd(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return affectedRows;
    }

    public ArrayList<Order> getOrdersByUserId(Connection connection,int userId) throws Exception{
        ArrayList<Order> orders = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(connection != null){
            String sql = "SELECT * from `order` WHERE userId = ?";
            Object[] params ={userId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                Order order=new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setMerchantId(rs.getInt("merchantId"));
//                order.setOrderStatus(rs.getInt("orderStatus"));
                if(rs.getInt("orderStatus")==0){
                    order.setOrderStatus("未完成");
                }else{
                    order.setOrderStatus("已完成");
                }
                //todo 这里可能需要一些互相调用的逻辑
//                order.setTotalPrice(rs.getFloat("totalPrice"));
                order.setOrderTime(rs.getDate("orderTime"));
                orders.add(order);
            }
            BaseDao.closeResource(null, pstm, rs);
            BaseDao.closeResource(null, pstm, rs);
        }
        return orders;
    }
    public List<Order> getUserOrderHistory(Connection connection,int userId) throws Exception{
        List<Order> orders = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(connection != null){
            String sql = "SELECT *\n" +
                    "FROM 'order'\n" +
                    "WHERE userId = ? and orderStatus=1";
            Object[] params ={userId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                Order order=new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setMerchantId(rs.getInt("merchantId"));
//                order.setOrderStatus(rs.getInt("orderStatus"));
//                order.setTotalPrice(rs.getFloat("totalPrice"));
                order.setOrderTime(rs.getTime("orderTime"));
                orders.add(order);
            }
            BaseDao.closeResource(null, pstm, rs);
            BaseDao.closeResource(null, pstm, rs);
        }
        return orders;
    }

}
