package pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DishPrice {
    private Boolean isDelete; // 表示是否删除
    private Integer id; // 历史价格id
    private Integer dishId; // 菜品id
    private Float price; // 价格，使用BigDecimal以保持精度
    private Date validStartTime; // 当前价格有效的开始时间

    // 构造函数
    public DishPrice() {
    }

    // Getters and Setters
    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }
}
