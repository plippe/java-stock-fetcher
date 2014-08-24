package com.secret.app.products;

import com.secret.app.enums.Currency;
import com.secret.app.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Index extends Product implements Serializable {

    private static final long serialVersionUID = 1251211219872297036L;
    private String indexName;

    public Index(String id, ProductType productType, Currency currency) {
        super(id, productType, currency);
    }
}
