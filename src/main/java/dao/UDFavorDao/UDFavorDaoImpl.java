package dao.UDFavorDao;

import dao.BaseDao;
import pojo.Order;
import pojo.UDFavor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UDFavorDaoImpl implements UDFavorDao{
    @Override
    public int addUDFavor(Connection connection, UDFavor udFavor) throws Exception {
        PreparedStatement pstm = null;
        int updateRows = 0;
        if(null != connection){
            String sql = "insert into udfavor (userId,dishId) " +
                    "values(?,?)";
            Object[] params = {udFavor.getUserId(),udFavor.getDishId()};
            updateRows = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return updateRows;
    }

    @Override
    public int deleteUDFavorById(Connection connection, int userId, int dishId) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "delete from udfavor where userId=? and dishId=?";
            Object[] params = {userId,dishId};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public ArrayList<UDFavor> getUDFavorsByUserId(Connection connection, int dishId, int currentPageNo, int pageSize) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<UDFavor> udFavorsList = new ArrayList<UDFavor>();
        if (connection != null) {
            String sql="select * from `udfavor` where dishId= ?  limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {dishId, currentPageNo,pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                UDFavor udFavor = new UDFavor();
                udFavor.setUserId(rs.getInt("userId"));
                udFavor.setDishId(rs.getInt("dishId"));
                udFavorsList.add(udFavor);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return udFavorsList;
    }
    public int getUDFavorTotalCountByDishId(Connection connection,int dishId) throws SQLException {
        int count=0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from `udfavor` where dishId=?";
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


    public ArrayList<Integer> getUserFavoriteDishIds(Connection connection,int userId) throws SQLException{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Integer> result =new ArrayList<>();
        if (null != connection) {
            String sql = "select dishId from `udfavour` where userId=?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {userId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                int count = rs.getInt(1);
                result.add(count);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return result;
    }
}
