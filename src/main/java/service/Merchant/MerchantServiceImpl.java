package service.Merchant;

import dao.BaseDao;
import dao.DishDao.DishDao;
import dao.DishDao.DishDaoImpl;
import dao.MerchantDao.MerchantDao;
import dao.MerchantDao.MerchantDaoImpl;
import pojo.Dish;
import pojo.Merchant;
import pojo.MerchantDetail;
import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//service层框架:
/*
Connection connection=null;
        MerchantDetail merchantDetail=new MerchantDetail();
        try{
            connection= BaseDao.getConnection();
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
 */
public class MerchantServiceImpl implements MerchantService {
    private final MerchantDao merchantDao=new MerchantDaoImpl();
    private final DishDao dishDao=new DishDaoImpl();
    @Override
    public ArrayList<Merchant> getSimpleMerchantByName(String name){
        //调用dao层
        Connection connection=null;
        ArrayList<Merchant> merchantList=new ArrayList<>();
        try{
            connection= BaseDao.getConnection();
            merchantList=merchantDao.getSimpleMerchantByName(connection,name);
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
        return merchantList;
    }

    @Override
    public MerchantDetail getDetailedMerchantById(Integer id){
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

    /**
     * @param merchant
     * @return 返回的是新建的merchant的id
     */
    @Override
    public int addMerchant(Merchant merchant){
        Connection connection=null;
        int newId=0;
        try{
            connection= BaseDao.getConnection();
            newId=merchantDao.addMerchant(connection,merchant);
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
    public boolean deleteMerchantById(int id){
        Connection connection=null;
        boolean result=false;
        try{
            connection= BaseDao.getConnection();
            result=merchantDao.logicDeleteMerchantById(connection,id);
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

    @Override
    public int modifyMerchantById(Merchant merchant) {
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=merchantDao.modifyMerchantById(connection,merchant);
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

    @Override
    public Merchant getMerchantById(int id) {
        Connection connection=null;
        Merchant merchant=null;
        try{
            connection= BaseDao.getConnection();
            merchant= merchantDao.getMerchantById(connection,id);
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

        return merchant;
    }

    @Override
    public int modifyMenuByMerchantId(int merchantId) {
        return 0;
    }

    @Override
    public boolean deleteDishByMerchantId(int merchantId) {
        return false;
    }

    @Override
    public ArrayList<Merchant> getAllMerchantList(int currentPageNo,int pageSize) {
        Connection connection=null;
        ArrayList<Merchant> merchantList=null;
        try{
            connection= BaseDao.getConnection();
            merchantList= merchantDao.getMerchantList(connection,currentPageNo, pageSize);
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
        return merchantList;
    }

    @Override
    public int getMerchantTotalCount() {
        Connection connection=null;
        int result=0;
        try{
            connection= BaseDao.getConnection();
            result=merchantDao.getMerchantTotalCount(connection);
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
