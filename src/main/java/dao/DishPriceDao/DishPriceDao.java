package dao.DishPriceDao;

import pojo.DishPrice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 这个类很简单，只要添加/搜索对应dishId的所有历史价格就行，不需要修改/删除
 */
public interface DishPriceDao {
    public int addDishOldPrice(Connection connection, DishPrice dishPrice) throws Exception;


    public ArrayList<DishPrice> getHistoryDishPriceById (Connection connection,int dishId) throws SQLException;

}
