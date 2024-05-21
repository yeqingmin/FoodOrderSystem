package dao.DishDao;

import pojo.User;

import java.sql.Connection;

public interface DishDao {
    /**
     * 通过dishId获取dish
     * @author:liuying
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    public User getDishById(Connection connection, int id)throws Exception;

}
