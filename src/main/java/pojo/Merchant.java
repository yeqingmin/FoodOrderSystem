package pojo;

public class Merchant {
    private Integer merchantId;
    private String merchantAddr;
    private String merchantName;
    private Boolean isDelete;

    public String getFeatureDish() {
        return featureDish;
    }

    public void setFeatureDish(String featureDish) {
        this.featureDish = featureDish;
    }

    private String featureDish;
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantAddr() {
        return merchantAddr;
    }

    public void setMerchantAddr(String merchantAddr) {
        this.merchantAddr = merchantAddr;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

}
