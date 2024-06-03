package dao.OrderDetailDao;

import dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            String sql = "SELECT o.userId\n" +
                    "FROM `order` AS o\n" +
                    "WHERE (\n" +
                    "    SELECT COUNT(*)\n" +
                    "    FROM `orderdetail` AS d\n" +
                    "    WHERE d.orderId = o.orderId\n and d.dishId = ?" +
                    ") = (\n" +
                    "    SELECT MAX(count_orders)\n" +
                    "    FROM (\n" +
                    "        SELECT o.userId, COUNT(*) AS count_orders\n" +
                    "        FROM `order` AS o1\n" +
                    "        JOIN `orderdetail` AS d1 where d1.orderId = o1.orderId and d1.dishId = ?" +
                    "        GROUP BY o1.userId\n" +
                    "    ) AS subquery\n" +
                    ")";
            /*String sql="    SELECT MAX(count_orders)\n" +
                    "    FROM (\n" +
                    "        SELECT o.userId, COUNT(*) AS count_orders\n" +
                    "        FROM `order` AS o1\n" +
                    "        JOIN `orderdetail` AS d1 where d1.orderId = o1.orderId and d1.dishId = ?" +
                    "        GROUP BY o1.userId\n" +
                    "    ) AS subquery\n"
                   ;*/
            pstm = connection.prepareStatement(sql);
            Object[] params = {dishId,dishId};
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

}
