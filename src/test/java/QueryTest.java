import dao.BaseDao;
import dao.DishDao.DishDao;
import dao.DishDao.DishDaoImpl;
import dao.MerchantDao.MerchantDao;
import dao.MerchantDao.MerchantDaoImpl;
import dao.UserDao.UserDao;
import dao.UserDao.UserDaoImpl;
import org.junit.Test;
import pojo.Dish;
import pojo.Merchant;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryTest {
    @Test
    public void merchantDaoQueryTest() throws SQLException {
        MerchantDao merchantDao=new MerchantDaoImpl();
        Connection connection=BaseDao.getConnection();
        ArrayList<Merchant> merchants= merchantDao.getSimpleMerchantByName(connection,"瑞幸咖啡");
        for(Merchant merchant:merchants){
            System.out.println(merchant);
        }
    }
    @Test
    public void dish_getDishByNameAndMerchantTest() throws Exception {
        DishDao dishDao=new DishDaoImpl();
        Connection connection=BaseDao.getConnection();
        Dish dish= dishDao.getDishByNameAndMerchant(connection,"燕麦拿铁",1);
        System.out.println(dish);
    }
    @Test
    public void user_getUserListTest() throws SQLException {
        Connection connection= BaseDao.getConnection();
        UserDao userDao=new UserDaoImpl();
        ArrayList<User>  userList= userDao.getUserList(connection);
        for(User user:userList){
            System.out.println(user);
        }
    }
}
