package dao.UserDao;

import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<User> getUserList(Connection connection,int currentPageNo, int pageSize) throws SQLException;

    public int getUserTotalCount(Connection connection) throws SQLException;
}
