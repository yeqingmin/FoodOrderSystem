package dao.MerchantDao;

import dao.BaseDao;
import pojo.Merchant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MerchantDaoImpl implements MerchantDao{


    @Override
    public ArrayList<Merchant> getSimpleMerchantByName(Connection connection, String name) throws SQLException {
        ArrayList<Merchant> merchants=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        if(null != connection){
            String sql="select * from Merchant where merchantName=?";
            Object params[]={name};
            resultSet=BaseDao.execute(connection,preparedStatement,resultSet,sql,params);
            if(resultSet.next()){
                Merchant merchant =new Merchant();
            }
        }
    }

    @Override
    public Merchant getDetailedMerchantByName(Connection connection, String name) {
        return null;
    }

    @Override
    public boolean addMerchant(Connection connection, Merchant merchant) throws Exception {
        PreparedStatement preparedStatement= null;
        int flag=0;
        if(null != connection){
            String sql= "insert into Merchant (merchantAddr) values(?)";
            Object params[]={merchant.getMerchantAddr()};
            flag= BaseDao.execute(connection,preparedStatement,sql,params);
            BaseDao.closeResource(null,preparedStatement,null);
        }
        if(flag!=0){
            System.out.println("add Merchant succeed!");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMerchantById(Connection connection, int id) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "delete from Merchant where id=?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        if(flag!=0){
            System.out.println("delete Merchant succeed！");
            return true;
        }
        return false;
    }

    @Override
    public int modifyMerchantById(Connection connection, Merchant merchant) {
        return 0;
    }

    @Override
    public Merchant getMerchantById(Connection connection, int id) {
        return null;
    }
}
