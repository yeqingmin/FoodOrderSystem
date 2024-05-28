package dao.UMReviewDao;

import dao.BaseDao;
import pojo.UMReview;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UMReviewDaoImpl implements UMReviewDao{
    public int addReview(Connection connection, UMReview review) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO `UMReview` (`merchantRating`, `merchantComment`, `isDelete`, `userId`, `merchantId`) " +
                    "VALUES (?, ?, ?, ?, ?)";
            Object[] params ={review.getMerchantRating(),review.getMerchantComment(),review.getIsDelete(),review.getUserId(),review.getMerchantId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public List<UMReview> getReviewsByBusinessName(Connection connection, String merchantName) throws Exception {
        List<UMReview> reviews = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // 准备SQL查询
        if(connection != null){
            String sql = "SELECT *\n" +
                    "FROM Merchant\n" +
                    "NATURAL JOIN UMReview\n" +
                    "WHERE Merchant.merchantName = ?";
            Object[] params ={merchantName};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                UMReview review = new UMReview();
                review.setMerchantRating(rs.getInt("merchantRating"));
                review.setMerchantComment(rs.getString("merchantComment"));
                review.setIsDelete(rs.getBoolean("isDelete"));
                review.setUserId(rs.getInt("userId"));
                review.setMerchantId(rs.getInt("MerchantId"));
                reviews.add(review);
            }
            BaseDao.closeResource(null, pstm, rs);
            BaseDao.closeResource(null, pstm, rs);
        }
        return reviews;
    }
    public int deleteReview(Connection connection, UMReview review)
    throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            int id=review.getReviewId();
            String sql = "delete from UMReview where reviewId=?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
    public int modifyReview(Connection connection, UMReview review)
            throws Exception {
        int flag = 0;
        int id =review.getReviewId();
        PreparedStatement pstm = null;
        if(null != connection){
            String sql = "UPDATE `UMReview` " +
                    "SET `merchantRating` = ?, " +
                    "`merchantComment` = ?, " +
                    "`isDelete` = ?, " +
                    "`userId` = ?, " +
                    "`merchantId` = ? " +
                    "WHERE `reviewId` = ?";
            Object[] params = {review.getMerchantRating(),review.getMerchantComment(),review.getIsDelete(),review.getUserId(),review.getMerchantId(),id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public List<UMReview> getReviewsByBusinessNameAndAddress(Connection connection, String merchantName , String address) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<UMReview> reviews = new ArrayList<>();
        if(connection != null){
            String sql = "SELECT *\n" +
                    "FROM Merchant\n" +
                    "NATURAL JOIN UMReview\n" +
                    "WHERE Merchant.merchantName = ? and Merchant.address = ?";
            Object[] params ={merchantName,address};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                UMReview review = new UMReview();
                review.setMerchantRating(rs.getInt("merchantRating"));
                review.setMerchantComment(rs.getString("merchantComment"));
                review.setIsDelete(rs.getBoolean("isDelete"));
                review.setUserId(rs.getInt("userId"));
                review.setMerchantId(rs.getInt("MerchantId"));
                reviews.add(review);
            }
            BaseDao.closeResource(null, pstm, rs);
            BaseDao.closeResource(null, pstm, rs);
        }
        return reviews;
    }
}
