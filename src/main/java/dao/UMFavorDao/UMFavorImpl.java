package dao.UMFavorDao;

import dao.BaseDao;
import pojo.UDFavor;
import pojo.UMFavor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UMFavorImpl implements UMFavorDao{
    @Override
    public int addUMFavor(Connection connection, UMFavor umFavor) throws Exception {
        PreparedStatement pstm = null;
        int updateRows = 0;
        if(null != connection){
            String sql = "insert into umfavor (userId,merchantId) " +
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
            String sql = "delete from umfavor where userId=? and merchantId=?";
            Object[] params = {userId,merchantId};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public ArrayList<UMFavor> getUMFavorsByMerchantId(Connection connection, int merchantId, int currentPageNo, int pageSize) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<UMFavor> umFavorsList = new ArrayList<UMFavor>();
        if (connection != null) {
            String sql="select * from `umfavor` where merchantId= ? order by OrderId limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {merchantId, currentPageNo,pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                UMFavor umFavor = new UMFavor();
                umFavor.setUserId(rs.getInt("userId"));
                umFavor.setMerchantId(rs.getInt("merchantId"));
                umFavorsList.add(umFavor);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return umFavorsList;
    }
}
