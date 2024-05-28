package service.User;

import dao.BaseDao;
import dao.DishDao.DishDao;
import dao.DishDao.DishDaoImpl;
import dao.MerchantDao.MerchantDao;
import dao.MerchantDao.MerchantDaoImpl;
import dao.OrderDao.OrderDao;
import dao.OrderDao.OrderDaoImpl;
import dao.UDFavorDao.UDFavorDao;
import dao.UDFavorDao.UDFavorDaoImpl;
import dao.UDReviewDao.UDReviewDao;
import dao.UDReviewDao.UDReviewDaoImpl;
import dao.UMFavorDao.UMFavorDao;
import dao.UMFavorDao.UMFavorImpl;
import dao.UMReviewDao.UMReviewDao;
import dao.UMReviewDao.UMReviewDaoImpl;
import dao.UserDao.UserDao;
import dao.UserDao.UserDaoImpl;
import pojo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao=new UserDaoImpl();

    private final MerchantDao merchantDao=new MerchantDaoImpl();

    private final DishDao dishDao=new DishDaoImpl();

    private final UMReviewDao umReviewDao=new UMReviewDaoImpl();

    private final OrderDao orderDao=new OrderDaoImpl();

    private final UDFavorDao udFavorDao=new UDFavorDaoImpl();

    private final UMFavorDao umFavorDao=new UMFavorImpl();

    private final UDReviewDao udReviewDao=new UDReviewDaoImpl();
    @Override
    //获取用户信息
    public User getUserById(int id) {
        Connection connection=null;
        User user=null;
        try{
            connection= BaseDao.getConnection();
            user= userDao.getUserById(connection,id);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return user;
    }

    @Override
    public ArrayList<User> getAllUserList() {
        Connection connection=null;
        ArrayList<User> userList=null;
        try{
            connection= BaseDao.getConnection();
            userList= userDao.getUserList(connection);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return userList;
    }

    //用户通过商户名字搜索商户简略信息
    public ArrayList<Merchant> getSeriesMerchantByName(String merchantName){
        Connection connection=null;
        ArrayList<Merchant> merchants=new ArrayList<Merchant>();
        try{
            connection= BaseDao.getConnection();
            merchants= merchantDao.getSimpleMerchantByName(connection,merchantName);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

        return merchants;
    }

    //用户根据商户名字和地址查看商户详细信息
    public MerchantDetail getMerchantDetailByNameAndAddress(String merchantName,String address){
        Connection connection=null;
        Merchant merchant=null;
        MerchantDetail merchantDetails=new MerchantDetail();
        ArrayList<Dish> menu=new ArrayList<Dish>();
        try{
            connection= BaseDao.getConnection();
            merchant= merchantDao.getMerchantByNameAndAddress(connection,merchantName,address);
            menu=dishDao.getDishByMerchantId(connection,merchant.getMerchantId());
            merchantDetails.setMerchant(merchant);
            merchantDetails.setMenu(menu);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return merchantDetails;
    }

    //根据商户地址和名称获得商户评价
    public UMReview getReviewByMerchantNameAndMerchantAddress( String merchantName,String address){
        Connection connection=null;
        UMReview umReview=new UMReview();
        try{
            connection= BaseDao.getConnection();
            umReview= umReviewDao.getReviewsByBusinessNameAndAddress(connection,merchantName,address);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return umReview;
    }

    //在一家饭店里搜索感兴趣的商品
    public Dish getInterestedDishInMerchant(String dishName , int id){
        Connection connection=null;
        Dish dish=null;
        try{
            connection= BaseDao.getConnection();
            dish= dishDao.getDishByNameAndMerchant(connection,dishName,id);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return dish;
    }

    //用户点单
    public void Order(int merchantId , int userId,Date orderTime, int state, ArrayList<Dish> dishes ,float price){
        Connection connection=null;
        Order order=new Order();
        order.setOrderTime(orderTime);
        order.setMerchantId(merchantId);
        order.setUserId(userId);
        order.setTotalPrice(price);
//        order.setOrderStatus(state);

        try{
            connection= BaseDao.getConnection();
            orderDao.addOrder(connection,order);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
    }


    //收藏菜品
    public void favouriteDish(int userId , int dishId){
        Connection connection=null;
        UDFavor udFavor=new UDFavor();
        udFavor.setDishId(dishId);
        udFavor.setUserId(userId);
        try{
            connection= BaseDao.getConnection();
            udFavorDao.addUDFavor(connection,udFavor);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
    }

    //收藏商家
    public void favouriteMerchant(int userId , int merchantId){
        Connection connection=null;
        UMFavor umFavor=new UMFavor();
        umFavor.setMerchantId(merchantId);
        umFavor.setUserId(userId);
        try{
            connection= BaseDao.getConnection();
            umFavorDao.addUMFavor(connection,umFavor);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
    }

    //对商家打分
    public void reviewMerchant(int userId,int merchantId , int rate , String comment){
        Connection connection=null;
        UMReview umReview =new UMReview();
        umReview.setMerchantRating(merchantId);
        umReview.setUserId(userId);
        umReview.setMerchantRating(rate);
        umReview.setMerchantComment(comment);
        try{
            connection= BaseDao.getConnection();
            umReviewDao.addReview(connection,umReview);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

    }

    //对菜品打分
    public void reviewDish(int userId,int dishId , int rate , String comment){
        Connection connection=null;
        UDReview udReview =new UDReview();
        udReview.setDishId(dishId);
        udReview.setUserId(userId);
        udReview.setDishRating(rate);
        udReview.setDishComment(comment);
        try{
            connection= BaseDao.getConnection();
            udReviewDao.addReview(connection,udReview);
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
    }

}
