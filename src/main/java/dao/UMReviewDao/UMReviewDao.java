package dao.UMReviewDao;
import pojo.UMReview;
import pojo.User;
import java.sql.Connection;
import java.util.List;

public interface UMReviewDao {
    /**
     * 用户对指定商户进行评价
     * @author:liuying
     * @param connection 数据库连接
     * @param review     包含用户ID、商户ID、评分和评价内容的评价对象
     * @return 插入评价后的记录ID，如果失败则返回-1或其他错误码
     * @throws Exception 如果数据库操作出错
     */
    public int addReview(Connection connection, UMReview review) throws Exception;

    /**
     * 根据商户名查询商户的评分和评价
     * @author:liuying
     * @param connection 数据库连接
     * @param merchantName 商户名
     * @return 包含评分和评价的商户列表，如果未找到则返回空列表
     * @throws Exception 如果数据库操作出错
     */
    public List<UMReview> getReviewsByBusinessName(Connection connection, String merchantName) throws Exception;

    /**
     * 用户删除指定评价
     * @author:liuying
     * @param connection 数据库连接
     * @param review   待删除评价的记录ID
     * @return 如果删除成功返回影响的记录数（通常为1），否则返回0或抛出异常
     * @throws Exception 如果数据库操作出错
     */
    public int deleteReview(Connection connection, UMReview review) throws Exception;

    /**
     * 用户修改指定评价
     * @author:liuying
     * @param connection 数据库连接
     * @param review     包含更新后的评分和评价内容（以及原始的评价ID）的评价对象
     * @return 如果修改成功返回影响的记录数（通常为1），否则返回0或抛出异常
     * @throws Exception 如果数据库操作出错
     */
    public int modifyReview(Connection connection, UMReview review) throws Exception;

}

