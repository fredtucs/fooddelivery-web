package org.wifry.fooddelivery.web.ws.model;

public class OrderProductModel {
    private String proId;
    private String name;

    public OrderProductModel() {
    }

    public OrderProductModel(String proId, String name) {
        this.proId = proId;
        this.name = name;
    }

    public String getProId() {
        return proId;
    }

    public OrderProductModel setProId(String proId) {
        this.proId = proId;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderProductModel setName(String name) {
        this.name = name;
        return this;
    }
}
