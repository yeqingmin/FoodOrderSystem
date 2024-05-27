import dao.BaseDao;
import org.junit.Test;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BaseDaoTest {
    @Test
    public void addTest() throws Exception {
        User user=new User();
        user.setUserGender("female");
        user.setUserName("cc");
        PreparedStatement pstm = null;
        Connection connection = BaseDao.getConnection();
        int flag = 0;
        String sql = "INSERT INTO user (userName, userGender) VALUES (?, ?)";
        Object[] params = {user.getUserName(), user.getUserGender()};
        flag = BaseDao.execute(connection, pstm, sql, params);
        System.out.println("new flag："+flag);
        BaseDao.closeResource(connection, pstm, null);
    }
}
