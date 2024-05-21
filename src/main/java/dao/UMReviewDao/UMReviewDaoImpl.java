package dao.UMReviewDao;

import dao.BaseDao;
import pojo.UMReview;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
