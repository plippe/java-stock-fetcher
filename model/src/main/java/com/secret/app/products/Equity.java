package com.secret.app.products;

import com.secret.app.enums.Currency;
import com.secret.app.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Equity extends Product {

    private String stockName;

    public Equity(String id, ProductType productType, Currency currency) {
        super(id, productType, currency);
    }
}
