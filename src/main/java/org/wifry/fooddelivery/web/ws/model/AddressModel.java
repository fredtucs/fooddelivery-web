package org.wifry.fooddelivery.web.ws.model;


public class AddressModel {

    private Long customerId;
    private Long addressId;
    private String addressDistrict;
    private String addressText;

    public AddressModel() {
    }

    public AddressModel(Long customerId, Long addressId, String addressDistrict, String addressText) {
        this.customerId = customerId;
        this.addressId = addressId;
        this.addressDistrict = addressDistrict;
        this.addressText = addressText;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }
}
