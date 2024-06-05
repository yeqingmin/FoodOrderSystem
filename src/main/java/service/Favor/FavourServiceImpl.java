package service.Favor;

import dao.BaseDao;
import dao.DishDao.DishDao;
import dao.DishDao.DishDaoImpl;
import dao.UDFavorDao.UDFavorDao;
import dao.UDFavorDao.UDFavorDaoImpl;
import dao.UMFavorDao.UMFavorDao;
import dao.UMFavorDao.UMFavorImpl;
import pojo.Dish;
import pojo.Order;
import pojo.UDFavor;
import pojo.UMFavor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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

    @Override
    public int getUDFavorTotalCountByDishId(Integer dishId) {
        Connection connection=null;
        int count=0;
        try{
            connection= BaseDao.getConnection();
            count=udFavorDao.getUDFavorTotalCountByDishId(connection,dishId);
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
        return count;
    }
    public int getUMFavorTotalCountByMerchantId(Integer merchantId) {
        Connection connection=null;
        int count=0;
        try{
            connection= BaseDao.getConnection();
            count=umFavorDao.getUMFavorTotalCountByMerchantId(connection,merchantId);
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
        return count;
    }
    public ArrayList<UDFavor> getUDFavorListByDishId(Integer dishId, int currentPageNo, int pageSize){
        Connection connection=null;
        ArrayList<UDFavor> udFavors = new ArrayList<>();
        try{
            connection= BaseDao.getConnection();
            udFavors=udFavorDao.getUDFavorsByUserId(connection,dishId,currentPageNo,pageSize);
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
        return udFavors;

    }

    public ArrayList<UMFavor> getUMFavorListByMerchantId(Integer merchantId, int currentPageNo, int pageSize){
        Connection connection=null;
        ArrayList<UMFavor> umFavors = new ArrayList<>();
        try{
            connection= BaseDao.getConnection();
            umFavors=umFavorDao.getUMFavorsByMerchantId(connection,merchantId,currentPageNo,pageSize);
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
        return umFavors;
    }
}
