package dao.UserDao;

import pojo.User;

import java.sql.Connection;

public interface UserDao {
    /**
     * 通过userId获取user
     * @author:liuying
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    public User getUserById(Connection connection, int id)throws Exception;

    /**
     * 增加用户
     * @author:liuying
     * @param connection
     * @param user
     * @return
     * @throws Exception
     */
    public int add(Connection connection, User user)throws Exception;

    /**
     * 通过userId删除User
     * @author:liuying
     * @return
     * @throws Exception
     */
    public int deleteUserById(Connection connection, int id)throws Exception;

    /**
     * 修改用户信息
     * @author:liuying
     * @param connection
     * @return
     * @throws Exception
     */
    public int modify(Connection connection, User user)throws Exception;
}
