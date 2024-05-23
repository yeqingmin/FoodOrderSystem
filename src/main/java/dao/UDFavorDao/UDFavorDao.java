package dao.UDFavorDao;

import pojo.UDFavor;
import pojo.UMFavor;

import java.sql.Connection;

public interface UDFavorDao {
    public int addUDFavor(Connection connection, UDFavor udFavor) throws Exception;
    public int deleteUDFavorById(Connection connection, int userId, int dishId)throws Exception;
}
