package service.Review;

import pojo.UDFavor;
import pojo.UDReview;
import pojo.UMReview;

import java.util.ArrayList;
import java.util.List;

public interface ReviewService {
    public int reviewMerchant(int userId,int merchantId , int rate , String comment);

    public int reviewDish(int userId,int dishId , int rate , String comment);

    public List<UMReview> getReviewByMerchantNameAndMerchantAddress(String merchantName, String address);

    public List<UDReview> getReviewByDishId(int dishId);

    public ArrayList<UDReview> getUDReviewListByDishId(Integer dishId, int currentPageNo, int pageSize);

    public ArrayList<UMReview> getUMReviewListByMerchantId(Integer merchantId, int currentPageNo, int pageSize);

    public int getUDReviewTotalCountByDishId(Integer dishId);
    public int getUMReviewTotalCountByMerchantId(Integer merchantId);
}
