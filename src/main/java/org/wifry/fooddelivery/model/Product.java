package org.wifry.fooddelivery.model;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Set;
import javax.persistence.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.SQLDelete;

@Entity
@Table
//@SQLDelete(sql = "UPDATE product SET is_active = 'N' WHERE product_id = ?")
@XmlRootElement
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeq")
    @SequenceGenerator(name = "productSeq", sequenceName = "PRODUCT_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "product_id", unique = true, nullable = false)
    private Long productId;

    @Column(name = "pro_name", length = 45)
    private String name;

    @Column(name = "pro_description", length = 150)
    private String proDescription;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "cat_id", referencedColumnName = "category_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEG"))
    private Category category;

    @Column(name = "current_price", precision = 10, scale = 2)
    private BigDecimal currentPrice;

    @Column(name = "last_version")
    private Integer lastVersion;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bucket.class)
    @JoinTable(name = "product_buckets", joinColumns = {
            @JoinColumn(name = "product_id", nullable = true, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "bucket_id", nullable = true, updatable = false)})
    private Bucket bucket;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ProductHistory.class)
    @JoinColumn(name = "product_id")
    private Set<ProductHistory> productHistorySet;

    public Product() {
    }

    public Product(Status status) {
        super(status);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(Integer lastVersion) {
        this.lastVersion = lastVersion;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public Set<ProductHistory> getProductHistorySet() {
        return productHistorySet;
    }

    public void setProductHistorySet(Set<ProductHistory> productHistorySet) {
        this.productHistorySet = productHistorySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (productId != null ? !productId.equals(product.productId) : product.productId != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (proDescription != null ? !proDescription.equals(product.proDescription) : product.proDescription != null) return false;
        if (currentPrice != null ? !currentPrice.equals(product.currentPrice) : product.currentPrice != null) return false;
        return lastVersion != null ? lastVersion.equals(product.lastVersion) : product.lastVersion == null;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (proDescription != null ? proDescription.hashCode() : 0);
        result = 31 * result + (currentPrice != null ? currentPrice.hashCode() : 0);
        result = 31 * result + (lastVersion != null ? lastVersion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + '}';
    }


}
