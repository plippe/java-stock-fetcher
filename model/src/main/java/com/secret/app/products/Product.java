package com.secret.app.products;

import com.secret.app.enums.Currency;
import com.secret.app.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public abstract class Product implements Serializable
{
    private static final long serialVersionUID = 7140440194431055196L;

    @EmbeddedId
    private ProductKey productKey;

    @Column(name="currency", nullable = true)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name="market_id", nullable = true)
    private String marketId;

    public Product(String id, ProductType productType, Currency currency)
    {
        this.productKey = new ProductKey(id, productType);
        this.currency = currency;
    }

    public void setId(String id)
    {
        this.productKey.setId(id);
    }

    public void setProductType(ProductType productType)
    {
        this.productKey.setProductType(productType);
    }

    public String getId()
    {
        return this.productKey.getId();
    }

    public ProductType getProductType()
    {
        return this.productKey.getProductType();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class ProductKey implements Serializable
    {
        private static final long serialVersionUID = 1211360949030265990L;

        @Column(name="product_id", nullable = false)
        private String id;

        @Column(name="product_type", nullable = false)
        @Enumerated(EnumType.STRING)
        private ProductType productType;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProductKey that = (ProductKey) o;

            if (!id.equals(that.id)) return false;
            if (productType != that.productType) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + productType.hashCode();
            return result;
        }
    }
}
