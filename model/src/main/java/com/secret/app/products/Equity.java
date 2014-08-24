package com.secret.app.products;

import com.secret.app.enums.Currency;
import com.secret.app.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Equity extends Product implements Serializable {

    private static final long serialVersionUID = -795118362875568384L;

    @Column(name="product_id", nullable = true)
    private String stockName;

    public Equity(String id, ProductType productType, Currency currency) {
        super(id, productType, currency);
    }
}
