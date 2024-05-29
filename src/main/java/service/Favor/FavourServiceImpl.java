package service.Favor;

import dao.BaseDao;
import dao.DishDao.DishDao;
import dao.DishDao.DishDaoImpl;
import dao.UDFavorDao.UDFavorDao;
import dao.UDFavorDao.UDFavorDaoImpl;
import dao.UMFavorDao.UMFavorDao;
import dao.UMFavorDao.UMFavorImpl;
import pojo.Dish;
import pojo.UDFavor;
import pojo.UMFavor;

import java.sql.Connection;
import java.sql.SQLException;

public class FavourServiceImpl implements FavourService{

    private final UDFavorDao udFavorDao=new UDFavorDaoImpl();

    private final UMFavorDao umFavorDao=new UMFavorImpl();

    private final DishDao dishDao = new DishDaoImpl();

    //收藏菜品
    public void favouriteDish(int userId , int dishId){
        Connection connection=null;
        UDFavor udFavor=new UDFavor();
        udFavor.setDishId(dishId);
        udFavor.setUserId(userId);
        try{
            connection= BaseDao.getConnection();
            Dish dish=dishDao.getDishById(connection,dishId);
            udFavorDao.addUDFavor(connection,udFavor);
            dishDao.increaseFavourNumber(connection,dish);
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
    }

    //收藏商家
    public void favouriteMerchant(int userId , int merchantId){
        Connection connection=null;
        UMFavor umFavor=new UMFavor();
        umFavor.setMerchantId(merchantId);
        umFavor.setUserId(userId);
        try{
            connection= BaseDao.getConnection();
            umFavorDao.addUMFavor(connection,umFavor);
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
    }
}
