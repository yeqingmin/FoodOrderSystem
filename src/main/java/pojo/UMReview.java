package pojo;

public class UMReview {
    private Integer reviewId; // 评价id
    private Integer merchantRating; // 对商家的评分
    private String merchantComment; // 用户对于商家的评价
    private Boolean isDelete; // 是否删除
    private Integer userId; // 用户id
    private Integer merchantId; // 商家id

    // 构造函数
    public UMReview() {
    }

    // Getters and Setters
    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(Integer merchantRating) {
        this.merchantRating = merchantRating;
    }

    public String getMerchantComment() {
        return merchantComment;
    }

    public void setMerchantComment(String merchantComment) {
        this.merchantComment = merchantComment;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    // toString 方法，用于打印对象信息
    @Override
    public String toString() {
        return "UMReview{" +
                "reviewId=" + reviewId +
                ", merchantRating=" + merchantRating +
                ", merchantComment='" + merchantComment + '\'' +
                ", isDelete=" + isDelete +
                ", userId=" + userId +
                ", merchantId=" + merchantId +
                '}';
    }
}