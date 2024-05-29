package dao.OrderDao;

import com.mysql.cj.util.StringUtils;
import dao.BaseDao;
import pojo.Order;
import pojo.UDReview;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public ArrayList<Order> getOrdersByUserId(Connection connection,int userId,int currentPageNo, int pageSize) throws Exception{
//        ArrayList<Order> orders = new ArrayList<>();
//        PreparedStatement pstm = null;
//        ResultSet rs = null;
//        if(connection != null){
//            String sql = "SELECT * from `order` WHERE userId = ?";
//            Object[] params ={userId};
//            rs = BaseDao.execute(connection, pstm, rs, sql, params);
//            while(rs.next()){
//                Order order=new Order();
//                order.setOrderId(rs.getInt("orderId"));
//                order.setUserId(rs.getInt("userId"));
//                order.setMerchantId(rs.getInt("merchantId"));
////                order.setOrderStatus(rs.getInt("orderStatus"));
//                if(rs.getInt("orderStatus")==0){
//                    order.setOrderStatus("未完成");
//                }else{
//                    order.setOrderStatus("已完成");
//                }
//                //todo 这里可能需要一些互相调用的逻辑
////                order.setTotalPrice(rs.getFloat("totalPrice"));
//                order.setOrderTime(rs.getDate("orderTime"));
//                orders.add(order);
//            }
//            BaseDao.closeResource(null, pstm, rs);
//        }
//        return orders;


        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Order> orderList = new ArrayList<Order>();
        if (connection != null) {
            String sql="select * from `order` where userId= ? order by OrderId limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {userId, currentPageNo,pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setMerchantId(rs.getInt("merchantId"));
                order.setUserId(rs.getInt("userId"));
                if(rs.getInt("orderStatus")==0){
                    order.setOrderStatus("未完成");
                }else{
                    order.setOrderStatus("已完成");
                }
                order.setOrderTime(rs.getDate("orderTime"));
                orderList.add(order);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return orderList;
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
                order.setOrderTime(rs.getDate("orderTime"));
                orders.add(order);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return orders;
    }

    @Override
    public int getOrderTotalCountByUserId(Connection connection,int userId) throws SQLException {
        int count=0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from `order` where userId= ?";
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
