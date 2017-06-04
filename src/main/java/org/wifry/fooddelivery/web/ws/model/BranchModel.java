package org.wifry.fooddelivery.web.ws.model;

/**
 * Created by Fredy on 2/06/2017.
 */
public class BranchModel {

    private Long branchId;
    private String branchName;
    private String branchAddress;

    public BranchModel() {
    }

    public BranchModel(Long branchId, String branchName, String branchAddress) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
