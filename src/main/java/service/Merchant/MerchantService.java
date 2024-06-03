package service.Merchant;

import pojo.Merchant;
import pojo.MerchantDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MerchantService {
    /**
     * 根据商户名字搜索出整个merchant的简略信息列表，将这个数组传给servlet，让它渲染
     * @param name
     * @return
     * @throws SQLException
     */
    public ArrayList<Merchant> getSimpleMerchantByName(String name);

    /**
     * 根据商户的名字和地址（目前暂时设计成id），获取对应的菜单（菜单每个菜品的菜品概要，这里不仅要调用MerchantDao还要调用DishDao）
     * @param id
     * @return
     * @throws SQLException
     */
    public MerchantDetail getDetailedMerchantById(Integer id);

    public int addMerchant(Merchant merchant);

    public boolean deleteMerchantById(int id);

    public int modifyMerchantById(Merchant merchant);

    public Merchant getMerchantById(int id);

    /**
     * 管理菜单，对对应merchantId的dish改查(比如修改菜品价格，修改分类）
     * @param merchantId
     * @return 修改的元组数
     */
    public int modifyMenuByMerchantId(int merchantId);

    /**
     * 管理菜单，对对应merchantId的dish进行删
     * @param merchantId
     * @return
     */
    public boolean deleteDishByMerchantId(int merchantId);

    public ArrayList<Merchant> getAllMerchantList(int currentPageNo,int pageSize);

    public int getMerchantTotalCount();



}
