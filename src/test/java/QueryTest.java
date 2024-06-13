import dao.BaseDao;
import dao.BookDao.BookDao;
import dao.BookMessageDao.BookMessageDao;
import dao.BookMessageDao.BookMessageDaoImpl;
import dao.DishDao.DishDao;
import dao.DishDao.DishDaoImpl;
import dao.MerchantDao.MerchantDao;
import dao.MerchantDao.MerchantDaoImpl;
import dao.OrderDao.OrderDao;
import dao.OrderDao.OrderDaoImpl;
import dao.OrderMessageDao.OrderMessageDao;
import dao.OrderMessageDao.OrderMessageDaoImpl;
import dao.UMReviewDao.UMReviewDao;
import dao.UMReviewDao.UMReviewDaoImpl;
import org.junit.Test;
import pojo.*;
import service.Dish.DishService;
import service.Dish.DishServiceImpl;
import service.Favor.FavourService;
import service.Favor.FavourServiceImpl;
import service.Order.OrderService;
import service.Order.OrderServiceImpl;
import service.Review.ReviewService;
import service.Review.ReviewServiceImpl;
import service.User.UserService;
import service.User.UserServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryTest {
    @Test
    public void merchantDaoQueryTest() throws SQLException {
        MerchantDao merchantDao=new MerchantDaoImpl();
        Connection connection=BaseDao.getConnection();
        ArrayList<Merchant> merchants= merchantDao.getSimpleMerchantByName(connection,"瑞幸咖啡");
        for(Merchant merchant:merchants){
            System.out.println(merchant);
        }
    }
    @Test
    public void dish_getDishByNameAndMerchantTest() throws Exception {
        DishDao dishDao=new DishDaoImpl();
        Connection connection=BaseDao.getConnection();
        Dish dish= dishDao.getDishByNameAndMerchant(connection,"燕麦拿铁",1);
        System.out.println(dish);
    }

    @Test
    public void getMessageTest() throws Exception {
        BookMessageDao orderDao=new BookMessageDaoImpl();
        Connection connection=BaseDao.getConnection();
        ArrayList<BookMessage> o1=new ArrayList<>();
        o1= orderDao.getBookMessage(connection,1);
        System.out.println(o1.get(0).getBookStatusMessage());
    }

    @Test
    public void reviewMerchantTest() throws SQLException {
        UMReviewDao umReviewDao=new UMReviewDaoImpl();
        Connection connection=BaseDao.getConnection();
        ReviewService u1= new ReviewServiceImpl();
        u1.reviewMerchant(1,1,10,"非常非常好吃");
    }

    @Test
    public void getMerchantReviewTest() throws SQLException {
        UserService u1= new UserServiceImpl();
        List<UMReview> u= new ArrayList<>();
        u=u1.getReviewByMerchantNameAndMerchantAddress("肯德基","政通路");
        System.out.println(u.size());
    }

    @Test
    public void reviewDishTest() throws SQLException {
        UserService u1= new UserServiceImpl();
        u1.reviewDish(1,1,10,"好吃");
    }

    @Test
    public void getDishReviewTest() throws SQLException{
        ReviewService r=new ReviewServiceImpl();
        List<UDReview> u=new ArrayList<>();
        u=r.getReviewByDishId(1);
        System.out.println(u.size());
    }

    @Test
   public void FavourTest(){
       FavourService f=new FavourServiceImpl();
       //f.favouriteMerchant(1,1);
       f.favouriteDish(1,1);
   }

   @Test
    public void orderTest(){
        OrderService o=new OrderServiceImpl();
        Order o1=new Order();
        o1.setMerchantId(1);
        o1.setUserId(2);
        o1.setOnline(true);
        o.addOrder(o1);
   }

   @Test
    public void getOfflineNumberTest(){
        int result=0;
       OrderService o=new OrderServiceImpl();
       result=o.getOfflineNumberByDishId(1);
       System.out.println(result);
   }

   @Test
    public void BiggestBuyers(){
       int result=0;
       OrderService o=new OrderServiceImpl();
       result=o.getBiggestBuyer(1);
       System.out.println(result);
   }

   @Test
    public void WeeklyTest(){
       int result=0;
       OrderService o=new OrderServiceImpl();
       result=o.getWeeklyOfflineNumber(1);
       System.out.println(result);
   }

    @Test
    public void LoyalBuyersTest(){
        ArrayList<Integer> list=new ArrayList<>();
        OrderService o=new OrderServiceImpl();
        list=o.getLoyalBuyers(1);
        System.out.println(list);
    }

    @Test
    public void LoyalBuyersDishNumbersTest(){
        int result=0;
        OrderService o=new OrderServiceImpl();
        result=o.getLoyalBuyersDishNumber(1,1);
        System.out.println(result);
    }

    @Test
    public void TestFrequency(){
        ArrayList<Integer> list=new ArrayList<>();
        OrderService o=new OrderServiceImpl();
        list=o.calculateWeeklyOrderFrequencyChanges(1);
        System.out.println(list);
    }

    @Test
    public  void TestFavour(){
        ArrayList<Integer> list=new ArrayList<>();
        FavourService f=new FavourServiceImpl();
        list=f.getUserFavoriteDishIds(1);
        System.out.println(list);
    }
}
