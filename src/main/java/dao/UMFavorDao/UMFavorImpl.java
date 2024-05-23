package dao.UMFavorDao;

import dao.BaseDao;
import pojo.UMFavor;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UMFavorImpl implements UMFavorDao{
    @Override
    public int addUMFavor(Connection connection, UMFavor umFavor) throws Exception {
        PreparedStatement pstm = null;
        int updateRows = 0;
        if(null != connection){
            String sql = "insert into UMFavor (userId,merchantId) " +
                    "values(?,?)";
            Object[] params = {umFavor.getUserId(),umFavor.getMerchantId()};
            updateRows = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return updateRows;
    }
    @Override
    public int deleteUMFavorById(Connection connection, int userId,int merchantId)
            throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "delete from UMFavor where userId=? and merchantId=?";
            Object[] params = {userId,merchantId};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }


}
