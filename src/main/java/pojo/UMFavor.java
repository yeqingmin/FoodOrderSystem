package pojo;

public class UMFavor {
    private Boolean isDelete; // 使用Boolean类型来表示是否删除
    private Integer userId; // 用户id
    private Integer merchantId; // 商家id

    // 构造函数
    public UMFavor() {
    }

    // Getters and Setters
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
        return "UMFavor{" +
                "isDelete=" + isDelete +
                ", userId=" + userId +
                ", merchantId=" + merchantId +
                '}';
    }
}
