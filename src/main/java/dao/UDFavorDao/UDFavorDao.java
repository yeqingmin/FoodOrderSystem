package dao.UDFavorDao;

import pojo.Order;
import pojo.UDFavor;
import pojo.UMFavor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UDFavorDao {
    public int addUDFavor(Connection connection, UDFavor udFavor) throws Exception;
    public int deleteUDFavorById(Connection connection, int userId, int dishId)throws Exception;
    public ArrayList<UDFavor> getUDFavorsByUserId(Connection connection, int dishId, int currentPageNo, int pageSize) throws Exception;
    public int getUDFavorTotalCountByDishId(Connection connection,int dishId) throws SQLException;

    //返回一个用户收藏的菜品Id
    public ArrayList<Integer> getUserFavoriteDishIds(Connection connection,int userId) throws SQLException;
}
