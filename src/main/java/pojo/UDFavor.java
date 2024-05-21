package pojo;

public class UDFavor {
    private Boolean isDelete; // 使用Boolean类型来表示是否删除
    private Integer dishId; // 菜品id
    private Integer userId; // 用户id

    // 构造函数
    public UDFavor() {
    }

    // Getters and Setters
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

}