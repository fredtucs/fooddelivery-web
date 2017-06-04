package org.wifry.fooddelivery.web.ws.model;

public class LoginSendModel {
    private String email;
    private Long id;

    public LoginSendModel(Long id, String email) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
