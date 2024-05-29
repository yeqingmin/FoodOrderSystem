package dao.MerchantDao;

import dao.BaseDao;
import pojo.Merchant;
import pojo.User;

import java.sql.*;
import java.util.ArrayList;

public class MerchantDaoImpl implements MerchantDao{

    /**
     * 根据商户名查看不同的商户简略信息
     * 简略信息包括商户名称和主打菜品
     * @param connection
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Merchant> getSimpleMerchantByName(Connection connection, String name) throws SQLException {
        ArrayList<Merchant> merchants=new ArrayList<>();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        if(null != connection){
            String sql="select * from merchant where isDelete = 0 and merchantName= ?";
            Object params[]={name};
            resultSet=BaseDao.execute(connection,preparedStatement,resultSet,sql,params);
            while(resultSet.next()){
                Merchant merchant =new Merchant();
                merchant.setMerchantId(Integer.valueOf(resultSet.getString("merchantId")));
                merchant.setMerchantName(resultSet.getString("merchantName"));
                merchant.setMerchantAddr(resultSet.getString("merchantAddr"));
//                merchant.setFeatureDish(resultSet.getString("featureDish"));
                merchants.add(merchant);
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
//        System.out.println("In MerchantDao,merchantList's size"+merchants.size());
        return merchants;
    }

    /**
     * 根据id查询一个商家的详细信息,包括菜单、菜品概要
     * @param connection
     * @param id
     * @return
     */
    @Override
    public Merchant getMerchantById(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement=null;
        Merchant merchant=null;
        ResultSet resultSet=null;
        if(null != connection){
            String sql="select * from merchant where isDelete = 0 and merchantId = ?";
            Object argms[]={id};
            resultSet=BaseDao.execute(connection,preparedStatement,resultSet,sql,argms);
            while(resultSet.next()){
                merchant=new Merchant();
                merchant.setMerchantId(id);
                merchant.setMerchantName(resultSet.getString("merchantName"));
                merchant.setMerchantAddr(resultSet.getString("merchantAddr"));
//                merchant.setFeatureDish(resultSet.getString("featureDish"));
            }
            BaseDao.closeResource(connection,preparedStatement,resultSet);
        }
        return merchant;
    }

    /**
     * 管理员调用增加商家
     * @param connection
     * @param merchant
     * @return
     * @throws Exception
     */
    @Override
    public int addMerchant(Connection connection, Merchant merchant) throws Exception {
        PreparedStatement preparedStatement= null;
        int newId=0;
        if(null != connection){
            String sql= "insert into merchant (merchantName,merchantAddr) values(?,?)";
            Object params[]={merchant.getMerchantName(), merchant.getMerchantAddr()};
            newId= BaseDao.executeAdd(connection,preparedStatement,sql,params);
            BaseDao.closeResource(null,preparedStatement,null);
        }
        return newId;
    }

    @Override
    public boolean logicDeleteMerchantById(Connection connection, int id) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            //String sql = "delete from merchant where merchantId = ?";
            String sql="update merchant set isDelete=1 where merchantId = ?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        if(flag!=0){
            System.out.println("logicDelete Merchant succeed！");
            return true;
        }
        return false;
    }

    @Override
    public int modifyMerchantById(Connection connection, Merchant merchant) throws Exception {
        int flag = 0;
        PreparedStatement pstm = null;
        if(null != connection){
            String sql = "update merchant set merchantAddr=?,merchantName=? where merchantId=?";
            Object[] params = {merchant.getMerchantAddr(),merchant.getMerchantName(),merchant.getMerchantId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public Merchant getMerchantByNameAndAddress(Connection connection,String name,String address) throws SQLException{
        PreparedStatement preparedStatement=null;
        Merchant merchant=null;
        ResultSet resultSet=null;
        if(null != connection){
            String sql="select * from merchant where isDelete = 0 and merchantAddr = ? and merchantName = ?";
            Object argms[]={address,name};
            resultSet=BaseDao.execute(connection,preparedStatement,resultSet,sql,argms);
            while(resultSet.next()){
                merchant=new Merchant();
                merchant.setMerchantId(resultSet.getInt("merchantId"));
                merchant.setMerchantName(resultSet.getString("merchantName"));
                merchant.setMerchantAddr(resultSet.getString("merchantAddr"));
                merchant.setFeatureDish(resultSet.getString("featureDish"));
            }
            BaseDao.closeResource(connection,preparedStatement,resultSet);
        }
        return merchant;
    }

    @Override
    public ArrayList<Merchant> getMerchantList(Connection connection, int currentPageNo, int pageSize) throws SQLException {
        //todo 原来的代码
        //分页查询的代码

        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Merchant> merchantList = new ArrayList<Merchant>();
        if (connection != null) {
            String sql="select * from merchant where isDelete = 0 order by merchantId limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {currentPageNo,pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                Merchant merchant = new Merchant();
                merchant.setMerchantId(rs.getInt("merchantId"));
                merchant.setMerchantName(rs.getString("merchantName"));
                merchant.setMerchantAddr(rs.getString("merchantAddr"));
                merchantList.add(merchant);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return merchantList;
    }

    @Override
    public int getMerchantTotalCount(Connection connection) throws SQLException {
        int count=0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from merchant where isDelete = 0";
            pstm = connection.prepareStatement(sql);
            Object[] params = {};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                count = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

}
