package dao.UDFavorDao;

import dao.BaseDao;
import pojo.UDFavor;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
