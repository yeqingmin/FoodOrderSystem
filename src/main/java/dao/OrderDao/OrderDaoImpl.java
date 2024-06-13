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
            String sql = "insert into `order` (userId, merchantId,isOnline) values (?, ?,?)";
            Object[] params ={order.getUserId(),order.getMerchantId(),order.getOnline()};
            affectedRows = BaseDao.executeAdd(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return affectedRows;
    }

    public ArrayList<Order> getOrdersByUserId(Connection connection,int userId,int currentPageNo, int pageSize) throws Exception{
       PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Order> orderList = new ArrayList<Order>();
        if (connection != null) {
            String sql="select * from `order` where userId= ? order by orderId limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {userId, currentPageNo,pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
//                order.setOnline(rs.getBoolean("isOnline"));
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

    public int getDishOnlineNumber(Connection connection,int dishId) throws SQLException{
        int count=0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from `orderdetail` as d ,`order` as o where o.orderId=d.orderId and o.isOnline=0 and d.dishId = ?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                count = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    public int getDisOfflineNumber(Connection connection,int dishId) throws SQLException{
        int count=0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from `orderdetail` as d ,`order` as o where o.orderId=d.orderId and o.isOnline=1 and d.dishId = ?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                count = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    public ArrayList<Integer> getLoyalBuyers(Connection connection,int merchantId) throws SQLException{
        ArrayList<Integer> buyers = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select distinct o.userId from `order` as o where (select count(*) from `order` as o1 where o1.merchantId= ? and o.userId=userId and orderTime >= CURDATE() - INTERVAL 30 DAY) >10";
            pstm = connection.prepareStatement(sql);
            Object[] params = {merchantId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()){
                int userId = rs.getInt("userId");
                buyers.add(userId);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return buyers;
    }

    public int LoyalUserDIshOrderNumbers(Connection connection,int userId,int dishId) throws SQLException{
        int count=0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from `order` as o ,`orderdetail` as d where o.orderId=d.orderId and o.userId=? and d.dishId = ?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {userId,dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()){
                count = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    public ArrayList<Integer> calculateMonthlyOrderFrequencyChanges(Connection connection,int userId) throws SQLException{
        ArrayList<Integer> frequencies = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (null != connection) {
            String sql1 = "select count(*) from `order`  where CURDATE() - INTERVAL 120 DAY >= orderTime >= CURDATE() - INTERVAL 90 DAY and userId=?";
            pstm = connection.prepareStatement(sql1);
            Object[] params1 = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql1, params1);
            if (rs.next()){
                count = rs.getInt(1);
                frequencies.add(count);
            }

            String sql2 = "select count(*) from `order`  where CURDATE() - INTERVAL 90 DAY >= orderTime >= CURDATE() - INTERVAL 60 DAY and userId=?";
            pstm = connection.prepareStatement(sql2);
            Object[] params2 = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql2, params2);
            if (rs.next()){
                count = rs.getInt(1);
                frequencies.add(count);
            }

            String sql3 = "select count(*) from `order`  where CURDATE() - INTERVAL 60 DAY >= orderTime >= CURDATE() - INTERVAL 30 DAY and userId=?";
            pstm = connection.prepareStatement(sql3);
            Object[] params3 = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql3, params3);
            if (rs.next()){
                count = rs.getInt(1);
                frequencies.add(count);
            }

            String sql4 = "select count(*) from `order`  where orderTime >= CURDATE() - INTERVAL 30 DAY and userId=?";
            pstm = connection.prepareStatement(sql4);
            Object[] params4 = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql4, params4);
            if (rs.next()){
                count = rs.getInt(1);
                frequencies.add(count);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return frequencies;
    }

    public ArrayList<Integer> calculateWeeklyOrderFrequencyChanges(Connection connection,int userId) throws SQLException{
        ArrayList<Integer> frequencies = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (null != connection) {
            String sql1 = "select count(*) from `order`  where CURDATE() - INTERVAL 21 DAY >= orderTime >= CURDATE() - INTERVAL 28 DAY and userId=?";
            pstm = connection.prepareStatement(sql1);
            Object[] params1 = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql1, params1);
            if (rs.next()){
                count = rs.getInt(1);
                frequencies.add(count);
            }

            String sql2 = "select count(*) from `order`  where CURDATE() - INTERVAL 14 DAY >= orderTime >= CURDATE() - INTERVAL 21 DAY and userId=?";
            pstm = connection.prepareStatement(sql2);
            Object[] params2 = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql2, params2);
            if (rs.next()){
                count = rs.getInt(1);
                frequencies.add(count);
            }

            String sql3 = "select count(*) from `order`  where CURDATE() - INTERVAL 7 DAY >= orderTime >= CURDATE() - INTERVAL 14 DAY and userId=?";
            pstm = connection.prepareStatement(sql3);
            Object[] params3 = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql3, params3);
            if (rs.next()){
                count = rs.getInt(1);
                frequencies.add(count);
            }

            String sql4 = "select count(*) from `order`  where orderTime >= CURDATE() - INTERVAL 7 DAY and userId=?";
            pstm = connection.prepareStatement(sql4);
            Object[] params4 = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql4, params4);
            if (rs.next()){
                count = rs.getInt(1);
                frequencies.add(count);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return frequencies;
    }
}
