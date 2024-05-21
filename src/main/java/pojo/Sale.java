package pojo;

public class Sale {
    private Boolean isDelete; // 使用Boolean类型来表示是否删除
    private Boolean isFeature; // 使用Boolean类型来表示是否主打
    private String type; // 菜品分类
    private Integer dishId; // 菜品id
    private Integer merchantId; // 商家id

    // 构造函数
    public Sale() {
    }

    // Getters and Setters
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getIsFeature() {
        return isFeature;
    }

    public void setIsFeature(Boolean isFeature) {
        this.isFeature = isFeature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
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
        return "Sale{" +
                "isDelete=" + isDelete +
                ", isFeature=" + isFeature +
                ", type='" + type + '\'' +
                ", dishId=" + dishId +
                ", merchantId=" + merchantId +
                '}';
    }
}