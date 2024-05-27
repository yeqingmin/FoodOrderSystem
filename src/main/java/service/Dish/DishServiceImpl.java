package service.Dish;

import dao.BaseDao;
import dao.DishDao.DishDao;
import dao.DishDao.DishDaoImpl;
import pojo.Dish;
import pojo.MerchantDetail;

import java.sql.Connection;
import java.sql.SQLException;

public class DishServiceImpl implements DishService{
    private final DishDao dishDao=new DishDaoImpl();
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
}
