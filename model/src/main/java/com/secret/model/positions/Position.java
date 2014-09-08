package com.secret.model.positions;

import com.secret.model.products.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Position {
    private final String portfolioId;
    private final Product product;
    private final Double quantity;
    private final Double costPrice;
}
