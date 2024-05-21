package dao.MerchantDao;

import pojo.Merchant;

import java.util.ArrayList;

public interface MerchantDao {
    /**
     * 获取商户名搜索出来
     *
     * @return
     */
    public ArrayList<Merchant> getSimpleMerchantByName(String name);

    public Merchant getDetailedMerchantByName();

    public boolean addMerchant();

    public boolean deleteMerchantById(int id);

    public int modifyMerchantById(Merchant merchant);

    public Merchant getMerchantById(int id);
}
