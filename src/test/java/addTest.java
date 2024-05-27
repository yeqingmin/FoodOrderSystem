import dao.BaseDao;
import dao.OrderDao.OrderDao;
import dao.OrderDao.OrderDaoImpl;
import org.junit.Test;
import pojo.Order;

import java.sql.Connection;

public class addTest {
    @Test
    public void addOrderTest() throws Exception {
        OrderDao orderDao=new OrderDaoImpl();
        Connection connection= BaseDao.getConnection();
        Order order=new Order();
        order.setUserId(2);
        order.setMerchantId(1);
        int newId = orderDao.addOrder(connection,order);
        System.out.println("newId="+newId);
    }
}
