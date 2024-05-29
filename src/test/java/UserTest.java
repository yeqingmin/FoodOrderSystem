import dao.BaseDao;
import dao.UserDao.UserDao;
import dao.UserDao.UserDaoImpl;
import org.junit.Test;
import service.User.UserService;
import service.User.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class UserTest {
    @Test
    public void getTotalUserCountTest() throws SQLException {
//        UserDao userDao=new UserDaoImpl();
        UserService userService=new UserServiceImpl();
        Connection connection= BaseDao.getConnection();
        int result=userService.getUserTotalCount();
        BaseDao.closeResource(connection,null,null);
        System.out.println(result);

    }
}
