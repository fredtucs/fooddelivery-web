package org.wifry.fooddelivery.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSeq")
    @SequenceGenerator(name = "orderSeq", sequenceName = "ORDERS_SEQ", initialValue = 10100, allocationSize = 1)
    @Column(name = "order_id", unique = true, nullable = false)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
    @JoinColumn(name = "addr_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Branch.class)
    @JoinColumn(name = "bran_id")
    private Branch branch;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "promo_code_id", length = 45)
    private String promoCodeId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time", length = 19)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time", length = 19)
    private Date endTime;

    @Column(name = "cost", precision = 19, scale = 2)
    private BigDecimal cost;

    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = OrderDetail.class)
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(Status status) {
        super(status);
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPromoCodeId() {
        return promoCodeId;
    }

    public void setPromoCodeId(String promoCodeId) {
        this.promoCodeId = promoCodeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) return false;
        if (paymentType != order.paymentType) return false;
        if (paymentStatus != order.paymentStatus) return false;
        if (promoCodeId != null ? !promoCodeId.equals(order.promoCodeId) : order.promoCodeId != null) return false;
        if (startTime != null ? !startTime.equals(order.startTime) : order.startTime != null) return false;
        if (endTime != null ? !endTime.equals(order.endTime) : order.endTime != null) return false;
        return cost != null ? cost.equals(order.cost) : order.cost == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (paymentStatus != null ? paymentStatus.hashCode() : 0);
        result = 31 * result + (promoCodeId != null ? promoCodeId.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    /*
     public String findOrderSummary(){
     StringBuilder sb = new StringBuilder();
     for(OrderDetails od : this.getOrderDetails()){
     if(od.getProduct() != null)
     sb.append(od.getUnitCount()).append( " x ").append(od.getProduct().getName());
     else if(od.getBucket() != null )
     sb.append(od.getUnitCount()).append( " x ").append(od.getBucket().getName());
			
     sb.append("   ");
     }
     return sb.toString();
     }
	
     */

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + '}';
    }
}
