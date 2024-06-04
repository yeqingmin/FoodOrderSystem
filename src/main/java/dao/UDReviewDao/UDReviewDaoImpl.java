package dao.UDReviewDao;

import dao.BaseDao;
import pojo.UDFavor;
import pojo.UDReview;
import pojo.UMReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UDReviewDaoImpl implements UDReviewDao{
    public int addReview(Connection connection, UDReview review) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            String sql = "INSERT INTO `udreview` (`dishRating`, `dishComment`, `dishId`, `userId`) " +
                    "VALUES ( ?, ?, ?, ?)";
            Object[] params ={review.getDishRating(),review.getDishComment(),review.getDishId(),review.getUserId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public List<UDReview> getReviewsById(Connection connection, int id) throws Exception {
        List<UDReview> reviews = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        // 准备SQL查询
        if(connection != null){
            String sql = "SELECT *\n" +
                    "FROM udreview\n" +
                    "WHERE dishId = ?";
            Object[] params ={id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while(rs.next()){
                UDReview review = new UDReview();
                review.setDishRating(rs.getInt("dishRating"));
                review.setDishComment(rs.getString("DISHComment"));
                review.setIsDelete(rs.getBoolean("isDelete"));
                review.setUserId(rs.getInt("userId"));
                review.setDishId(rs.getInt("DishId"));
                reviews.add(review);
            }
            BaseDao.closeResource(null, pstm, rs);
            BaseDao.closeResource(null, pstm, rs);
        }
        return reviews;
    }
    public int deleteReview(Connection connection, UDReview review)
            throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != connection){
            int id=review.getReviewId();
            String sql = "delete from udreview where reviewId=?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }
    public int modifyReview(Connection connection, UDReview review)
            throws Exception {
        int flag = 0;
        int id =review.getReviewId();
        PreparedStatement pstm = null;
        if(null != connection){
            String sql = "UPDATE `udreview` " +
                    "SET `dishRating` = ?, " +
                    "`dishComment` = ?, " +
                    "`isDelete` = ?, " +
                    "`userId` = ?, " +
                    "`dishId` = ? " +
                    "WHERE `reviewId` = ?";
            Object[] params = {review.getDishRating(),review.getDishComment(),review.getIsDelete(),review.getUserId(),review.getDishId(),id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    public ArrayList<UDReview> getUDReviewsByDishId(Connection connection, int dishId, int currentPageNo, int pageSize) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<UDReview> udReviewArrayList = new ArrayList<UDReview>();
        if (connection != null) {
            String sql="select * from `udreview` where dishId= ? order by reviewId limit ?,?";
            currentPageNo = (currentPageNo - 1) * pageSize;

            Object[] params = {dishId, currentPageNo,pageSize};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            while (rs.next()) {
                UDReview udReview = new UDReview();
                udReview.setReviewId(rs.getInt("reviewId"));
                udReview.setDishId(rs.getInt("dishId"));
                udReview.setUserId(rs.getInt("userId"));
                udReview.setDishRating(rs.getInt("dishRating"));
                udReview.setDishComment(rs.getString("dishComment"));
                udReviewArrayList.add(udReview);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return udReviewArrayList;
    }
    public int getUDReviewTotalCountByDishId(Connection connection,int dishId) throws SQLException {
        int count=0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select count(*) from `udreview` where dishId=?";
            pstm = connection.prepareStatement(sql);
            Object[] params = {dishId};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if(rs.next()){
                count = rs.getInt(1);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

}
