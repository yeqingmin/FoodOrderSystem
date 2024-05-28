package service.Favor;

import dao.BaseDao;
import dao.UDFavorDao.UDFavorDao;
import dao.UDFavorDao.UDFavorDaoImpl;
import dao.UMFavorDao.UMFavorDao;
import dao.UMFavorDao.UMFavorImpl;
import pojo.UDFavor;
import pojo.UMFavor;

import java.sql.Connection;
import java.sql.SQLException;

public class FavourServiceImpl implements FavourService{

    private final UDFavorDao udFavorDao=new UDFavorDaoImpl();

    private final UMFavorDao umFavorDao=new UMFavorImpl();

    //收藏菜品
    public void favouriteDish(int userId , int dishId){
        Connection connection=null;
        UDFavor udFavor=new UDFavor();
        udFavor.setDishId(dishId);
        udFavor.setUserId(userId);
        try{
            connection= BaseDao.getConnection();
            udFavorDao.addUDFavor(connection,udFavor);
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
