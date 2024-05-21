package dao.MerchantDao;

import pojo.Merchant;

import java.util.ArrayList;

public class MerchantDaoImpl implements MerchantDao{
    @Override
    public ArrayList<Merchant> getSimpleMerchantByName(String name) {
        /**
         * 根据用户输入的商户信息查询所有重名的商家
         */

        return null;
    }

    @Override
    public Merchant getDetailedMerchantByName() {
        return null;
    }

    @Override
    public boolean addMerchant() {
        return false;
    }

    @Override
    public boolean deleteMerchantById(int id) {
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

}
