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
@Table(name = "T_PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
public abstract class Product implements Serializable
{
    private static final long serialVersionUID = 7140440194431055196L;

    @Id
    @Column(name="product_id", nullable = false)
    private String id;

    @Column(name="product_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name="currency", nullable = true)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name="market_id", nullable = true)
    private String marketId;

    public Product(String id, ProductType productType, Currency currency)
    {
        this.id = id;
        this.currency = currency;
        this.productType = productType;
    }
}
