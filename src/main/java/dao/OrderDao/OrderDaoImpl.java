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
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO `Order` (`userId`, `merchantId`, `orderStatus`, `totalPrice`, `orderTime`) VALUES (?, ?, ?, ?, ?)";
            Object[] params ={order.getUserId(),order.getMerchantId(),order.getOrderStatus(),order.getTotalPrice(),order.getOrderTime()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public List<Order> getOrdersByUserId(Connection connection,int userId) throws Exception{
        List<Order> orders = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(connection != null){
            String sql = "SELECT *\n" +
                    "FROM Order\n" +
                    "WHERE userId = ? and orderStatus=0";
            Object[] params ={userId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                Order order=new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setMerchantId(rs.getInt("merchantId"));
                order.setOrderStatus(rs.getInt("orderStatus"));
                order.setTotalPrice(rs.getFloat("totalPrice"));
                order.setOrderTime(rs.getTime("orderTime"));
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
                    "FROM Order\n" +
                    "WHERE userId = ? and orderStatus=1";
            Object[] params ={userId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                Order order=new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setMerchantId(rs.getInt("merchantId"));
                order.setOrderStatus(rs.getInt("orderStatus"));
                order.setTotalPrice(rs.getFloat("totalPrice"));
                order.setOrderTime(rs.getTime("orderTime"));
                orders.add(order);
            }
            BaseDao.closeResource(null, pstm, rs);
            BaseDao.closeResource(null, pstm, rs);
        }
        return orders;
    }

}
