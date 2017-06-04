package org.wifry.fooddelivery.web.ws.model;

public class GetCustomerAddressModel {
    private String id;
    private String email;
    private String token;

    public GetCustomerAddressModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
