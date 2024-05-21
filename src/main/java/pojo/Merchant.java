package pojo;

public class Merchant {
    private Integer merchantId;
    private String merchantAddr;
    private Boolean isDelete;


    public Merchant() {}

    public Merchant(Integer merchantId, String merchantAddr, Boolean isDelete) {
        this.merchantId = merchantId;
        this.merchantAddr = merchantAddr;
        this.isDelete = isDelete;
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
