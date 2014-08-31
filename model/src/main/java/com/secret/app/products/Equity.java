package com.secret.app.products;

import com.secret.app.enums.Currency;
import com.secret.app.enums.ProductType;
import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Entity
public class Equity extends Product implements Serializable {

    private static final long serialVersionUID = -795118362875568384L;

    @Column(name="product_id", nullable = true)
    private final String stockName;

    public Equity(String id, ProductType productType, Currency currency, String marketId, String stockName) {
        super(id, productType, currency,marketId);
        this.stockName =stockName;
    }
}
