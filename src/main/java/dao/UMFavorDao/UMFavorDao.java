package dao.UMFavorDao;

import pojo.UMFavor;

import java.sql.Connection;

public interface UMFavorDao {
    public int addUMFavor(Connection connection, UMFavor umFavor) throws Exception;
    public int deleteUMFavorById(Connection connection, int userId, int merchantId)throws Exception;
}
