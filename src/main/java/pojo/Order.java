package pojo;

import java.util.Date;

public class Order {
    private Boolean isDelete; // 使用Boolean类型来表示是否删除
    private Integer orderId; // 订单id
    private Integer userId; // 用户id
    private Integer merchantId; // 商家id
    private String orderStatus; // 订单状态
    private Float totalPrice; // 订单总价
    private Date orderTime; // 订单时间
    private Boolean isOnline;

    // 构造函数
    public Order() {
    }

    // Getters and Setters
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }


    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "isDelete=" + isDelete +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", merchantId=" + merchantId +
                ", orderStatus='" + orderStatus + '\'' +
                ", totalPrice=" + totalPrice +
                ", orderTime=" + orderTime +
                '}';
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }
}
