package service.Order;

import dao.BaseDao;
import dao.OrderDao.OrderDao;
import dao.OrderDao.OrderDaoImpl;
import pojo.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<Order> getOrderListByUserId(Integer userId,int currentPageNo, int pageSize) {
        //查看自己的所有订单信息
            Connection connection=null;
            ArrayList<Order> orders = new ArrayList<>();
            try{
                connection= BaseDao.getConnection();
                orders=orderDao.getOrdersByUserId(connection,userId,currentPageNo,pageSize);
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
            return orders;
    }

    @Override
    public int getOrderTotalCountById(int userId) {
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDao.getOrderTotalCountByUserId(connection,userId);
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
        return result;
    }
}
