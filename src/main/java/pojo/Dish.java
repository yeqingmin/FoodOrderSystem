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
    private Integer totalCount;

    private int onlineSales;
    private int offlineSales;
    private String mostUser;
    public Dish() {}



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


    public Integer getDishFavourNumber() {
        return dishFavourNumber;
    }

    public void setDishFavourNumber(Integer dishFavourNumber) {
        this.dishFavourNumber = dishFavourNumber;
    }



    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public int getOnlineSales() {
        return onlineSales;
    }

    public void setOnlineSales(int onlineSales) {
        this.onlineSales = onlineSales;
    }

    public int getOfflineSales() {
        return offlineSales;
    }

    public void setOfflineSales(int offlineSales) {
        this.offlineSales = offlineSales;
    }

    public String getMostUser() {
        return mostUser;
    }

    public void setMostUser(String mostUser) {
        this.mostUser = mostUser;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishName='" + dishName + '\'' +
                ", onlineSales=" + onlineSales +
                ", offlineSales=" + offlineSales +
                ", mostUser='" + mostUser + '\'' +
                '}';
    }
}
