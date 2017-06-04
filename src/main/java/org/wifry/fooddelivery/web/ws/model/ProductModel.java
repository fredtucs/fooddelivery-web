package org.wifry.fooddelivery.web.ws.model;

import java.math.BigDecimal;

public class ProductModel {

    private Long productId;
    private String proName;
    private String proDesc;
    private BigDecimal currentPrice;

    public ProductModel() {
    }

    public ProductModel(Long productId, String proName, String proDesc, BigDecimal currentPrice) {
        this.productId = productId;
        this.proName = proName;
        this.proDesc = proDesc;
        this.currentPrice = currentPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
