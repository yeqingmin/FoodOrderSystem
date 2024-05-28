package dao.LoginDao;

import pojo.Login;

import java.sql.Connection;

/**
 * 当前为登录表操作
 */
public interface LoginDao {
    public int addLogin(Connection connection, Login login) throws Exception;
    public Login getLoginUser(Connection connection, String name, String password) throws Exception;
    public int deleteUserById(Connection connection, String name , String password) throws Exception;
}
