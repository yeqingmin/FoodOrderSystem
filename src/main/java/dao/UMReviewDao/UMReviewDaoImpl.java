package dao.UMReviewDao;

import dao.BaseDao;
import pojo.UDReview;
import pojo.UMReview;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UMReviewDaoImpl implements UMReviewDao{
    public int addReview(Connection connection, UMReview review) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO `umreview` (merchantRating, merchantComment, userId, merchantId) VALUES (?, ?, ?, ?)";
            Object[] params ={review.getMerchantRating(),review.getMerchantComment(),review.getUserId(),review.getMerchantId()};
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
                    "FROM merchant\n" +
                    "NATURAL JOIN umreview\n" +
                    "WHERE merchant.merchantName = ?";
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
            String sql = "delete from umreview where reviewId=?";
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
            String sql = "UPDATE `umreview` " +
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
                    "FROM merchant\n" +
                    "NATURAL JOIN umreview\n" +
                    "WHERE merchant.merchantName = ? and Merchant.merchantAddr = ?";
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

    public ArrayList<UMReview> getUMReviewByMerchantId(Connection connection, int merchantId, int currentPageNo, int pageSize) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<UMReview> umReviewArrayList = new ArrayList<UMReview>();
        if (connection != null) {
            String sql="select * from `umreview` where merchantId= ? order by reviewId limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {merchantId, currentPageNo,pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                UMReview review = new UMReview();
                review.setMerchantRating(rs.getInt("merchantRating"));
                review.setMerchantComment(rs.getString("merchantComment"));
                review.setIsDelete(rs.getBoolean("isDelete"));
                review.setUserId(rs.getInt("userId"));
                review.setMerchantId(rs.getInt("MerchantId"));
                review.setReviewId(rs.getInt("reviewId"));
                umReviewArrayList.add(review);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return umReviewArrayList;
    }
    public int getUMReviewTotalCountByMerchantId(Connection connection,int merchantId) throws SQLException {
        int count=0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from `umreview` where merchantId=?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {merchantId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                count = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }
}
