package pojo;

public class UDReview {
    private Boolean isDelete; // 使用Boolean类型来表示是否删除
    private Integer reviewId; // 评价id
    private Integer dishRating; // 对菜品的评分
    private String dishComment; // 用户对于菜品的评价
    private Integer dishId; // 菜品id
    private Integer userId; // 用户id

    // 构造函数
    public UDReview() {
    }

    // Getters and Setters
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getDishRating() {
        return dishRating;
    }

    public void setDishRating(Integer dishRating) {
        this.dishRating = dishRating;
    }

    public String getDishComment() {
        return dishComment;
    }

    public void setDishComment(String dishComment) {
        this.dishComment = dishComment;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // toString 方法，用于打印对象信息
    @Override
    public String toString() {
        return "UDReview{" +
                "isDelete=" + isDelete +
                ", reviewId=" + reviewId +
                ", dishRating=" + dishRating +
                ", dishComment='" + dishComment + '\'' +
                ", dishId=" + dishId +
                ", userId=" + userId +
                '}';
    }
}