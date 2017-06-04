package org.wifry.fooddelivery.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@XmlRootElement
public class OrderDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderDetailSeq")
    @SequenceGenerator(name = "orderDetailSeq", sequenceName = "ORDER_DETAIL_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "order_detail_id", unique = true, nullable = false)
    private Long orderDetailId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bucket.class)
    @JoinColumn(name = "bucket_id", referencedColumnName = "bucket_id")
    private Bucket bucket;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = EmployeeDelivery.class)
    @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
    private EmployeeDelivery employeeDelivery;

    @Column(name = "unit_count")
    private Integer unitCount;

    @Column(name = "unit_price", precision = 19, scale = 2)
    private BigDecimal unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(Product product, Integer unitCount, BigDecimal unitPrice, Status status, Order order) {
        super(status);
        this.order = order;
        this.product = product;
        this.unitCount = unitCount;
        this.unitPrice = unitPrice;
    }

    public OrderDetail(Status status) {
        super(status);
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public EmployeeDelivery getEmployeeDelivery() {
        return employeeDelivery;
    }

    public void setEmployeeDelivery(EmployeeDelivery employeeDelivery) {
        this.employeeDelivery = employeeDelivery;
    }

    public Integer getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getOrderPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(unitCount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail)) return false;

        OrderDetail detail = (OrderDetail) o;

        if (orderDetailId != null ? !orderDetailId.equals(detail.orderDetailId) : detail.orderDetailId != null) return false;
        if (product != null ? !product.equals(detail.product) : detail.product != null) return false;
        if (unitCount != null ? !unitCount.equals(detail.unitCount) : detail.unitCount != null) return false;
        return unitPrice != null ? unitPrice.equals(detail.unitPrice) : detail.unitPrice == null;
    }

    @Override
    public int hashCode() {
        int result = orderDetailId != null ? orderDetailId.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (unitCount != null ? unitCount.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderDetailId=" + orderDetailId + '}';
    }

}
