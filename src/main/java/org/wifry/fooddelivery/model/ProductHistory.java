package org.wifry.fooddelivery.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@XmlRootElement
public class ProductHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productHistorySeq")
    @SequenceGenerator(name = "productHistorySeq", sequenceName = "PRODUCT_HISTORY_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "history_id", unique = true, nullable = false)
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "version")
    private Integer version;

    public ProductHistory() {
    }

    public ProductHistory(Long historyId, Product product, String name, BigDecimal price, Integer version) {
        this.historyId = historyId;
        this.product = product;
        this.name = name;
        this.price = price;
        this.version = version;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((historyId == null) ? 0 : historyId.hashCode());
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
        ProductHistory other = (ProductHistory) obj;
        if (historyId == null) {
            if (other.historyId != null) {
                return false;
            }
        } else if (!historyId.equals(other.historyId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductHistory{" + "historyId=" + historyId + '}';
    }

}
