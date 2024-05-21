package dao.DishDao;

import pojo.Dish;
import pojo.User;

import java.sql.Connection;

public interface DishDao {
    /**
     * 通过dishId获取dish
     * @author:liuying
     * @param connection
     * @param name
     * @return
     * @throws Exception
     */
    public Dish getDishById(Connection connection, String name)throws Exception;
    public int add(Connection connection, Dish dish)throws Exception;
    public int modifyPrice(Connection connection, int id , float price)throws Exception;
    public int modifyCategory(Connection connection, int id , String dishCategory)throws Exception;
    public int deleteDishById(Connection connection, int id)throws Exception;
}
