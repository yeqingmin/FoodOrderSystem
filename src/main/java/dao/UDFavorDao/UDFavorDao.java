package dao.UDFavorDao;

import pojo.Order;
import pojo.UDFavor;
import pojo.UMFavor;

import java.sql.Connection;
import java.util.ArrayList;

public interface UDFavorDao {
    public int addUDFavor(Connection connection, UDFavor udFavor) throws Exception;
    public int deleteUDFavorById(Connection connection, int userId, int dishId)throws Exception;
    public ArrayList<UDFavor> getUDFavorsByUserId(Connection connection, int dishId, int currentPageNo, int pageSize) throws Exception;
}
