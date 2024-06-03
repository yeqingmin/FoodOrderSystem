package service.Review;

import dao.BaseDao;
import dao.UDFavorDao.UDFavorDao;
import dao.UDFavorDao.UDFavorDaoImpl;
import dao.UDReviewDao.UDReviewDao;
import dao.UDReviewDao.UDReviewDaoImpl;
import dao.UMReviewDao.UMReviewDao;
import dao.UMReviewDao.UMReviewDaoImpl;
import pojo.UDReview;
import pojo.UMReview;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private final UMReviewDao umReviewDao = new UMReviewDaoImpl();
    private final UDReviewDao udReviewDao = new UDReviewDaoImpl();

    public int reviewMerchant(int userId, int merchantId, int rate, String comment) {
        Connection connection = null;
        UMReview umReview = new UMReview();
        umReview.setMerchantId(merchantId);
        umReview.setUserId(userId);
        umReview.setMerchantRating(rate);
        umReview.setMerchantComment(comment);
        int result = 0;
        try {
            connection = BaseDao.getConnection();
            result = umReviewDao.addReview(connection, umReview);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return result;
    }

    //对菜品打分
    public int reviewDish(int userId, int dishId, int rate, String comment) {
        Connection connection = null;
        UDReview udReview = new UDReview();
        udReview.setDishId(dishId);
        udReview.setUserId(userId);
        udReview.setDishRating(rate);
        udReview.setDishComment(comment);
        int result = 0;
        try {
            connection = BaseDao.getConnection();
            result = udReviewDao.addReview(connection, udReview);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return result;
    }

    //根据商户地址和名称获得商户评价
    public List<UMReview> getReviewByMerchantNameAndMerchantAddress(String merchantName, String address) {
        Connection connection = null;
        List<UMReview> umReview = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            umReview = umReviewDao.getReviewsByBusinessNameAndAddress(connection, merchantName, address);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return umReview;
    }

    //查询菜品评价和评分
    public List<UDReview> getReviewByDishId(int dishId) {
        Connection connection = null;
        List<UDReview> udReview = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            udReview = udReviewDao.getReviewsById(connection, dishId);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return udReview;
    }


}
