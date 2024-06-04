package service.Order;

import dao.BaseDao;
import dao.OrderDao.OrderDao;
import dao.OrderDao.OrderDaoImpl;
import dao.OrderDetailDao.OrderDetailDao;
import dao.OrderDetailDao.OrderDetailDaoImpl;
import pojo.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService{
    OrderDao orderDao=new OrderDaoImpl();
    OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
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

    public int getOnlineNumberByDishId(int dishId){
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDao.getDishOnlineNumber(connection,dishId);
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

    public int getOfflineNumberByDishId(int dishId){
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDao.getDisOfflineNumber(connection,dishId);
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

    public int getBiggestBuyer(int dishId){
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDetailDao.biggestBuyerOfDish(connection,dishId);
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

    public int getWeeklyOnlineNumber(int dishId){
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDetailDao.weeklyOnlineDishNumber(connection,dishId);
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

    public int getWeeklyOfflineNumber(int dishId){
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDetailDao.weeklyOfflineDishNumber(connection,dishId);
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

    public int getMonthlyOnlineNumber(int dishId){
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDetailDao.monthlyOnlineDishNumber(connection,dishId);
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

    public int getMonthlyOfflineNumber(int dishId){
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDetailDao.monthlyOfflineDishNumber(connection,dishId);
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
    public int getYearlyOnlineNumber(int dishId){
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDetailDao.yearlyOnlineDishNumber(connection,dishId);
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

    public int getYearlyOfflineNumber(int dishId){
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDetailDao.yearlyOfflineDishNumber(connection,dishId);
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

    public ArrayList<Integer> getLoyalBuyers(int merchantId){
        Connection connection=null;
        ArrayList<Integer> result=new ArrayList<>();
        try{
            connection= BaseDao.getConnection();
            result=orderDao.getLoyalBuyers(connection,merchantId);
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

    public int getLoyalBuyersDishNumber(int userId ,int dishId){
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=orderDao.LoyalUserDIshOrderNumbers(connection,userId,dishId);
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
        return result;
    }
}
