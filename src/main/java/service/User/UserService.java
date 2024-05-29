package service.User;

import pojo.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface UserService {
    public int add(User user);
    public User getUserById(int id);

    public int modify(User user);

    public int deleteUserById(int id);

    public ArrayList<User> getAllUserList(int currentPageNo, int pageSize);

    public ArrayList<Merchant> getSeriesMerchantByName(String merchantName);

    public MerchantDetail getMerchantDetailByNameAndAddress(String merchantName , String address);

    public List<UMReview> getReviewByMerchantNameAndMerchantAddress(String merchantName,String address);

    public Dish getInterestedDishInMerchant(String dishName , int id);

    public void Order(int merchantId , int userId , Date orderTime,int state,ArrayList<Dish> dishes ,float price);

//    public List<Order> getOrder(int userId);

    public void favouriteDish(int userId , int dishId);

    public void favouriteMerchant(int userId , int merchantId);

    public void reviewMerchant(int userId,int merchantId , int rate , String comment);

    public void reviewDish(int userId,int dishId , int rate , String comment);
    public int getUserTotalCount();
}
