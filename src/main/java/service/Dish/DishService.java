package service.Dish;

import pojo.Dish;

import java.sql.Connection;
import java.util.ArrayList;

public interface DishService {
    public Dish getDishByNameAndMerchant(String dishName, int merchantId);

    public Dish getDishById(int id);

    public ArrayList<Dish> getDishByMerchantId(int merchantId);

    public int addDishToOrder(Integer dishId,Integer orderId);

    public int deleteDishFromOrder(Integer dishId,Integer orderId);

    public int countDishQuantity(Integer dishId,Integer orderId);

    public int getDishTotalCountByMerchantId(int merchantId);

    public ArrayList<Dish> getDishListByMerchantId(int merchantId,int currentPageNo,int pageSize);

    public int modifyDishById(Dish dish);

    public int deleteDishById(int dishId);

}
