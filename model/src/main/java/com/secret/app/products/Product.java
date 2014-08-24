package com.secret.app.products;

import com.secret.app.enums.Currency;
import com.secret.app.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public abstract class Product
{
    private ProductKey productKey;
    private Currency currency;
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
    public static class ProductKey
    {
        private String id;
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
