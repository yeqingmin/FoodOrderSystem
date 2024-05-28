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
        int flag = 0;
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
}
