package pojo;

import java.util.Arrays;

public class Dish {
    private Integer dishId;
    private String dishName;
    private Float dishPrice;
    private String dishCategory;
    private String dishDescription;
    private byte[] dishImage; // Using byte array to represent BLOB data
    private Integer merchantId;
    private String dishAllergens;
    private String dishIngredients;
    private String dishNutrition;
    private Boolean isDelete;
    private Integer dishFavourNumber;
    // Constructors, Getters, and Setters
    public Dish() {}

    public Dish(Integer dishId, String dishName, Float dishPrice, String dishCategory, String dishDescription,
                Integer dishFavourNumber,
                byte[] dishImage, Integer merchantId, Boolean isDelete  ) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishCategory = dishCategory;
        this.dishDescription = dishDescription;
        System.out.println(this.dishFavourNumber);
        this.dishFavourNumber=dishFavourNumber;
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

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", dishPrice=" + dishPrice +
                ", dishCategory='" + dishCategory + '\'' +
                ", dishDescription='" + dishDescription + '\'' +
                ", dishImage=" + Arrays.toString(dishImage) +
                ", merchantId=" + merchantId +
                ", isDelete=" + isDelete +
                '}';
    }

    public String getDishAllergens() {
        return dishAllergens;
    }

    public void setDishAllergens(String dishAllergens) {
        this.dishAllergens = dishAllergens;
    }

    public String getDishIngredients() {
        return dishIngredients;
    }

    public void setDishIngredients(String dishIngredients) {
        this.dishIngredients = dishIngredients;
    }

    public String getDishNutrition() {
        return dishNutrition;
    }

    public void setDishNutrition(String dishNutrition) {
        this.dishNutrition = dishNutrition;
    }

    public int getFavourNumber() {
        return dishFavourNumber;
    }

    public void setFavourNumber(int favourNumber) {
        this.dishFavourNumber = favourNumber;
    }
}
