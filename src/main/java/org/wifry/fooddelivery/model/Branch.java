package org.wifry.fooddelivery.model;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@XmlRootElement
public class Branch extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branchSeq")
    @SequenceGenerator(name = "branchSeq", sequenceName = "BRANCH_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "branch_id", unique = true, nullable = false)
    private Long branchId;

    @Column(name = "name", length = 45)
    private String name;

    @Temporal(TemporalType.TIME)
    @Column(name = "open_hour", length = 4)
    private Date openHour;

    @Temporal(TemporalType.TIME)
    @Column(name = "close_hour", length = 4)
    private Date closeHour;

    @Column(name = "manager_name", length = 45)
    private String managerName;

    @Column(name = "branch_address", length = 200)
    private String branchAddress;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, targetEntity = Order.class)
    @JoinColumn(name = "bran_id")
    private Set<Order> orders;

    public Branch() {
    }

    public Branch(Status status) {
        super(status);
    }

    public Branch(Status status, String name, Date openHour, Date closeHour, String managerName, String branchAddress) {
        super(status);
        this.name = name;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.managerName = managerName;
        this.branchAddress = branchAddress;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOpenHour() {
        return openHour;
    }

    public void setOpenHour(Date openHour) {
        this.openHour = openHour;
    }

    public Date getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(Date closeHour) {
        this.closeHour = closeHour;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Branch other = (Branch) obj;
        if (branchId == null) {
            if (other.branchId != null) {
                return false;
            }
        } else if (!branchId.equals(other.branchId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Branch{" + "branchId=" + branchId + '}';
    }

}
