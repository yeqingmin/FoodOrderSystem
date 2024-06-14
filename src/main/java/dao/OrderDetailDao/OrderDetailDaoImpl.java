package dao.OrderDetailDao;

import dao.BaseDao;
import pojo.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDaoImpl implements OrderDetailDao{

    @Override
    public int addDishToOrder(Connection connection, Integer dishId, Integer orderId) throws Exception {
        PreparedStatement pstm = null;
        int updatedRows = 0;
        if(null != connection){
            String sql = "insert into `orderdetail` (dishId, orderId) values (?, ?)";
            Object[] params ={dishId,orderId};
            updatedRows = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return updatedRows;
    }

    @Override
    public int deleteDishFromOrder(Connection connection, Integer dishId, Integer orderId) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "delete from orderdetail where dishId=? and orderId=? limit 1";
            Object[] params = {dishId,orderId};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    @Override
    public int countDishInOrderQuantities(Connection connection, Integer dishId, Integer orderId) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs=null;
        int quantity=0;
        if (null != connection) {
            String sql = "select count(*) from `orderdetail` where dishId=? and orderId=?";
            Object[] params = {dishId,orderId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                quantity = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, null);
        }
        return quantity;
    }

    public int biggestBuyerOfDish(Connection connection,int dishId) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs=null;
        int userId=-1;
        if (null != connection) {
            String sql = "select o.userId from `order` o join `orderdetail` od on o.orderId=od.orderId where od.dishId=? group by o.userId order by  count(*) desc limit 1";
            pstm = connection.prepareStatement(sql);
            Object[] params = {dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                System.out.println("!!!!!");
                userId = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, null);
        }
        return userId;
    }

    public int weeklyOnlineDishNumber(Connection connection,int dishId) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs=null;
        int number=0;
        if (null != connection) {
            String sql = "select count(*) from `orderdetail` as d, `order` as o where d.orderId = o.orderId and d.dishId=? and o.orderTime >= CURDATE() - INTERVAL 7 DAY and o.isOnline=0";
            Object[] params = {dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                number = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, null);
        }
        return number;
    }

    public int weeklyOfflineDishNumber(Connection connection,int dishId) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs=null;
        int number=0;
        if (null != connection) {
            String sql = "select count(*) from `orderdetail` as d, `order` as o where d.orderId = o.orderId and d.dishId=? and o.orderTime >= CURDATE() - INTERVAL 7 DAY and o.isOnline=1";
            Object[] params = {dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                number = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, null);
        }
        return number;
    }

    public int monthlyOfflineDishNumber(Connection connection,int dishId) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs=null;
        int number=0;
        if (null != connection) {
            String sql = "select count(*) from `orderdetail` as d, `order` as o where d.orderId = o.orderId and d.dishId=? and o.orderTime >= CURDATE() - INTERVAL 30 DAY and o.isOnline=1";
            Object[] params = {dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                number = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, null);
        }
        return number;
    }

    public int monthlyOnlineDishNumber(Connection connection,int dishId) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs=null;
        int number=0;
        if (null != connection) {
            String sql = "select count(*) from `orderdetail` as d, `order` as o where d.orderId = o.orderId and d.dishId=? and o.orderTime >= CURDATE() - INTERVAL 30 DAY and o.isOnline=0";
            Object[] params = {dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                number = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, null);
        }
        return number;
    }

    public int yearlyOfflineDishNumber(Connection connection,int dishId) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs=null;
        int number=0;
        if (null != connection) {
            String sql = "select count(*) from `orderdetail` as d, `order` as o where d.orderId = o.orderId and d.dishId=? and o.orderTime >= CURDATE() - INTERVAL 1 YEAR and o.isOnline=1";
            Object[] params = {dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                number = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, null);
        }
        return number;
    }

    public int yearlyOnlineDishNumber(Connection connection,int dishId) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs=null;
        int number=0;
        if (null != connection) {
            String sql = "select count(*) from `orderdetail` as d, `order` as o where d.orderId = o.orderId and d.dishId=? and o.orderTime >= CURDATE() - INTERVAL 1 YEAR and o.isOnline=10";
            Object[] params = {dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                number = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, null);
        }
        return number;
    }

    public ArrayList<OrderDetail> getDetailsByOrderId(Connection connection,int orderId) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs=null;
        ArrayList<OrderDetail> details=new ArrayList<>();
        int number=0;
        if (null != connection) {
            String sql = "select * from `orderdetail` where orderId=?";
            Object[] params = {orderId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                OrderDetail orderDetail=new OrderDetail();
                orderDetail.setOrderId(orderId);
                orderDetail.setDishId(rs.getInt("dishId"));
                details.add(orderDetail);
            }
            BaseDao.closeResource(null, pstm, null);
        }
        return details;
    }
}
