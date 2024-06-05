package dao.UMFavorDao;

import pojo.UDReview;
import pojo.UMFavor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UMFavorDao {
    public int addUMFavor(Connection connection, UMFavor umFavor) throws Exception;

    public int deleteUMFavorById(Connection connection, int userId, int merchantId) throws Exception;

    public ArrayList<UMFavor> getUMFavorsByMerchantId(Connection connection, int merchantId, int currentPageNo, int pageSize) throws Exception;

    public int getUMFavorTotalCountByMerchantId(Connection connection, int merchantId) throws SQLException;
}
