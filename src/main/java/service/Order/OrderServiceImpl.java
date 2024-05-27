package service.Order;

import dao.BaseDao;
import dao.OrderDao.OrderDao;
import dao.OrderDao.OrderDaoImpl;
import pojo.Order;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService{
    OrderDao orderDao=new OrderDaoImpl();
    @Override
    public int addOrder(Order order) {
        Connection connection=null;
        int newId = 0;
        try{
            connection= BaseDao.getConnection();
            newId=orderDao.addOrder(connection,order);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return newId;
    }
}
