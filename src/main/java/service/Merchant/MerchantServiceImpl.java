package service.Merchant;

import dao.BaseDao;
import dao.DishDao.DishDao;
import dao.DishDao.DishDaoImpl;
import dao.MerchantDao.MerchantDao;
import dao.MerchantDao.MerchantDaoImpl;
import pojo.Dish;
import pojo.Merchant;
import pojo.MerchantDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MerchantServiceImpl implements MerchantService {
    private final MerchantDao merchantDao=new MerchantDaoImpl();
    private final DishDao dishDao=new DishDaoImpl();
    @Override
    public ArrayList<Merchant> getSimpleMerchantByName(String name){
        return null;
    }

    @Override
    public MerchantDetail getDetailedMerchantById(int id){
        //这个方法不仅要调用MerchantDao还要调用DishDao
        Connection connection=null;
        ArrayList<Dish> menu=new ArrayList<Dish>();
        Merchant merchant=null;
        MerchantDetail merchantDetail=new MerchantDetail();
        try{
            connection= BaseDao.getConnection();
            merchant= merchantDao.getMerchantById(connection,id);
            menu=dishDao.getDishByMerchantId(connection,merchant.getMerchantId());
            merchantDetail.setMerchant(merchant);
            merchantDetail.setMenu(menu);
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

        return merchantDetail;
    }

    @Override
    public boolean addMerchant(Merchant merchant){
        return false;
    }

    @Override
    public boolean deleteMerchantById(int id){
        return false;
    }

    @Override
    public int modifyMerchantById(Merchant merchant) {
        return 0;
    }

    @Override
    public Merchant getMerchantById(int id) {
        return null;
    }

    @Override
    public int modifyMenuByMerchantId(int merchantId) {
        return 0;
    }

    @Override
    public boolean deleteDishByMerchantId(int merchantId) {
        return false;
    }
}
