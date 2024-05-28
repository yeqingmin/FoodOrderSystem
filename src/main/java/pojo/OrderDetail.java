package pojo;

public class OrderDetail {

    private Integer id; // 订单细节表的主键ID
    private Integer dishId; // 菜品ID
    private Integer orderId; // 订单ID

    // 无参构造函数
    public OrderDetail() {
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
