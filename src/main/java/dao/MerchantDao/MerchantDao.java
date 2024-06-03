package dao.MerchantDao;

import pojo.Merchant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MerchantDao {
    /**
     * 获取商户名搜索出来
     *
     * @return
     */
    public ArrayList<Merchant> getSimpleMerchantByName(Connection connection,String name) throws SQLException;

    public int addMerchant(Connection connection,Merchant merchant) throws Exception;

    public boolean logicDeleteMerchantById(Connection connection,int id) throws Exception;

    public int modifyMerchantById(Connection connection,Merchant merchant) throws Exception;

    public Merchant getMerchantById(Connection connection,int id) throws SQLException;

    public Merchant getMerchantByNameAndAddress(Connection connection,String name,String address) throws SQLException;

    public ArrayList<Merchant> getMerchantList(Connection connection, int currentPageNo, int pageSize) throws SQLException;
    public int getMerchantTotalCount(Connection connection) throws SQLException;
}
