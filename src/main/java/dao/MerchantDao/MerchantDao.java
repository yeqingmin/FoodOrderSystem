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

    public Merchant getDetailedMerchantByName(Connection connection,String name);

    public boolean addMerchant(Connection connection,Merchant merchant) throws Exception;

    public boolean deleteMerchantById(Connection connection,int id) throws Exception;

    public int modifyMerchantById(Connection connection,Merchant merchant);

    public Merchant getMerchantById(Connection connection,int id);
}
