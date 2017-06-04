package org.wifry.fooddelivery.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.SQLDelete;

@Entity
//@SQLDelete(sql = "UPDATE employee SET is_active = N WHERE employee_id = ?")
@XmlRootElement
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSeq")
    @SequenceGenerator(name = "employeeSeq", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
    @Column(name = "employee_id", unique = true, nullable = false)
    private Long employeeId;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "surname", length = 45)
    private String surname;

    @Column(name = "title", length = 45)
    private String title;

    @Column(name = "is_active", length = 1)
    private String isActive;

    @Temporal(TemporalType.DATE)
    @Column(name = "onboard_time")
    private Date onboardTime;

    @Column(name = "is_delivering")
    private Boolean isDelivering;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = EmployeeDelivery.class)
    @JoinColumn(name = "employee_id")
    private Set<EmployeeDelivery> employeeDeliveries;

    public Employee() {
    }

    public Employee(Long employeeId, String name, String surname, String title, String isActive, Date onboardTime, Boolean isDelivering, Set<EmployeeDelivery> employeeDeliveries) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.isActive = isActive;
        this.onboardTime = onboardTime;
        this.isDelivering = isDelivering;
        this.employeeDeliveries = employeeDeliveries;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Date getOnboardTime() {
        return onboardTime;
    }

    public void setOnboardTime(Date onboardTime) {
        this.onboardTime = onboardTime;
    }

    public Boolean getIsDelivering() {
        return isDelivering;
    }

    public void setIsDelivering(Boolean isDelivering) {
        this.isDelivering = isDelivering;
    }

    @XmlTransient
    public Set<EmployeeDelivery> getEmployeeDeliveries() {
        return employeeDeliveries;
    }

    public void setEmployeeDeliveries(Set<EmployeeDelivery> employeeDeliveries) {
        this.employeeDeliveries = employeeDeliveries;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
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
        Employee other = (Employee) obj;
        if (employeeId == null) {
            if (other.employeeId != null) {
                return false;
            }
        } else if (!employeeId.equals(other.employeeId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + '}';
    }

}
