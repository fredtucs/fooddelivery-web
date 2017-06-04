package org.wifry.fooddelivery.web.ws.model;

import java.util.List;

public class OrderCompleteModel {

    private String customerId;
    private String email;
    private String token;
    private String branchId;
    private String branchName;
    private List<OrderProductModel> sendProModels;
    private String addressId;

    public OrderCompleteModel() {
    }

    public OrderCompleteModel(String customerId, String email, String token, String branchId, String branchName, List<OrderProductModel> sendProModels, String addressId) {
        this.customerId = customerId;
        this.email = email;
        this.token = token;
        this.branchId = branchId;
        this.branchName = branchName;
        this.sendProModels = sendProModels;
        this.addressId = addressId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }


    public String getAddressId() {
        return addressId;
    }

    public OrderCompleteModel setAddressId(String addressId) {
        this.addressId = addressId;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public OrderCompleteModel setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OrderCompleteModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getToken() {
        return token;
    }

    public OrderCompleteModel setToken(String token) {
        this.token = token;
        return this;
    }

    public String getBranchName() {
        return branchName;
    }

    public OrderCompleteModel setBranchName(String branchName) {
        this.branchName = branchName;
        return this;
    }

    public List<OrderProductModel> getSendProModels() {
        return sendProModels;
    }

    public OrderCompleteModel setSendProModels(List<OrderProductModel> sendProModels) {
        this.sendProModels = sendProModels;
        return this;
    }


}
