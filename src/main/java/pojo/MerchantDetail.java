package pojo;

import java.util.ArrayList;

/**
 * 当前类为商户详细信息类，主要返回商户的基本信息和对应的菜单
 */
public class MerchantDetail {
    /**
     * 当前用户
     */
    private Merchant merchant;
    /**
     * 当前用户的菜单
     */
    private ArrayList<Dish> menu;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public ArrayList<Dish> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Dish> menu) {
        this.menu = menu;
    }
}
