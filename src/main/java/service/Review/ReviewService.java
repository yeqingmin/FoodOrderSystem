package service.Review;

import pojo.UDReview;
import pojo.UMReview;

import java.util.List;

public interface ReviewService {
    public int reviewMerchant(int userId,int merchantId , int rate , String comment);

    public int reviewDish(int userId,int dishId , int rate , String comment);

    public List<UMReview> getReviewByMerchantNameAndMerchantAddress(String merchantName, String address);

    public List<UDReview> getReviewByDishId(int dishId);
}
