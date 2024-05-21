package pojo;

public class Dish {
    private Integer dishId;
    private String dishName;
    private Float dishPrice;
    private String dishCategory;
    private String dishDescription;
    private byte[] dishImage; // Using byte array to represent BLOB data
    private Integer merchantId;
    private Boolean isDelete;

    // Constructors, Getters, and Setters
    public Dish() {}

    public Dish(Integer dishId, String dishName, Float dishPrice, String dishCategory, String dishDescription,
                byte[] dishImage, Integer merchantId, Boolean isDelete) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishCategory = dishCategory;
        this.dishDescription = dishDescription;
        this.dishImage = dishImage;
        this.merchantId = merchantId;
        this.isDelete = isDelete;
    }

    public Integer getDishId() {
        return dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public Float getDishPrice() {
        return dishPrice;
    }

    public String getDishCategory() {
        return dishCategory;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public byte[] getDishImage() {
        return dishImage;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    // Setters
    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setDishPrice(Float dishPrice) {
        this.dishPrice = dishPrice;
    }

    public void setDishCategory(String dishCategory) {
        this.dishCategory = dishCategory;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public void setDishImage(byte[] dishImage) {
        this.dishImage = dishImage;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}
