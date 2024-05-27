package service.Dish;

import pojo.Dish;

import java.sql.Connection;

public interface DishService {
    public Dish getDishByNameAndMerchant(String dishName, int merchantId);

    public Dish getDishById(int id);
}
