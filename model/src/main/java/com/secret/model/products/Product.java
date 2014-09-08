package com.secret.model.products;

import com.secret.model.enums.Currency;
import com.secret.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "T_PRODUCT")
@AllArgsConstructor
public abstract class Product implements Serializable
{
    private static final long serialVersionUID = 7140440194431055196L;

    @Id
    @Column(name="product_id", nullable = false)
    private final String id;

    @Column(name="product_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private final ProductType productType;

    @Column(name="currency", nullable = true)
    @Enumerated(EnumType.STRING)
    private final Currency currency;

    @Column(name="market_id", nullable = true)
    private final String marketId;
}
