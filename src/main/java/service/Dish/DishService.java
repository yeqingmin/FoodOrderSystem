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
}
