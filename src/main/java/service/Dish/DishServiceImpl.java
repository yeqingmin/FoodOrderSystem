package service.Dish;

import dao.BaseDao;
import dao.DishDao.DishDao;
import dao.DishDao.DishDaoImpl;
import dao.OrderDetailDao.OrderDetailDao;
import dao.OrderDetailDao.OrderDetailDaoImpl;
import pojo.Dish;
import pojo.MerchantDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DishServiceImpl implements DishService{
    private final DishDao dishDao=new DishDaoImpl();
    private final OrderDetailDao orderDetailDao=new OrderDetailDaoImpl();
    @Override
    public Dish getDishByNameAndMerchant(String dishName, int merchantId){
        Connection connection=null;
        Dish dish=null;
        try{
            connection= BaseDao.getConnection();
            dish=dishDao.getDishByNameAndMerchant(connection,dishName,merchantId);
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
        return dish;
    }

    @Override
    public Dish getDishById(int id) {
        Connection connection=null;
        Dish dish=null;
        try{
            connection= BaseDao.getConnection();
            dish=dishDao.getDishById(connection,id);
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
        return dish;
    }

    @Override
    public ArrayList<Dish> getDishByMerchantId(int merchantId) {
        Connection connection=null;
        ArrayList<Dish> menu=null;
        try{
            connection= BaseDao.getConnection();
            menu=dishDao.getDishByMerchantId(connection,merchantId);;
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
        return menu;
    }

    @Override
    public int addDishToOrder(Integer dishId, Integer orderId) {
        Connection connection=null;
        int updatedRows=0;
        try{
            connection= BaseDao.getConnection();
            updatedRows=orderDetailDao.addDishToOrder(connection,dishId,orderId);
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
        return updatedRows;
    }

    @Override
    public int deleteDishFromOrder(Integer dishId, Integer orderId) {
        Connection connection=null;
        int updatedRows=0;
        try{
            connection= BaseDao.getConnection();
            updatedRows=orderDetailDao.deleteDishFromOrder(connection,dishId,orderId);
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
        return updatedRows;
    }
}
