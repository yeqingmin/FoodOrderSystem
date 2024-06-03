package service.Favor;

import pojo.Order;
import pojo.UDFavor;
import pojo.UMFavor;

import java.util.ArrayList;

public interface FavourService {
    public void favouriteDish(int userId , int dishId);
    public void favouriteMerchant(int userId , int merchantId);
    public ArrayList<UDFavor> getUDFavorListByDishId(Integer dishId, int currentPageNo, int pageSize);
    public ArrayList<UMFavor> getUMFavorListByMerchantId(Integer merchantId, int currentPageNo, int pageSize);
}
