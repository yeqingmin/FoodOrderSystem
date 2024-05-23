package service.Login;

import pojo.Login;

import java.sql.Connection;

public interface LoginService {
    public int addLogin(Login login);
    public Login getLoginUser(String name, String password);
    public int deleteUserById(Integer delId);
}
