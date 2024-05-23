package service.Login;

import dao.BaseDao;
import dao.LoginDao.LoginDao;
import dao.LoginDao.LoginDaoImpl;
import pojo.Dish;
import pojo.Login;
import pojo.Merchant;
import pojo.MerchantDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginServiceImpl implements LoginService{
    private final LoginDao loginDao=new LoginDaoImpl();
    @Override
    public int addLogin(Login login) {
        return 0;
    }

    @Override
    public Login getLoginUser(String name, String password){
        Connection connection=null;
        Login login=null;
        try{
            connection= BaseDao.getConnection();
            login=loginDao.getLoginUser(connection,name,password);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

        return login;
    }

    @Override
    public int deleteUserById(Integer delId){
        return 0;
    }
}
