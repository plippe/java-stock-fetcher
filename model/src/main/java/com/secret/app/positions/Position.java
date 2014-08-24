package com.secret.app.positions;

import com.secret.app.products.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Position {
    private String portfolioId;
    private Product product;
    private Double quantity;
    private Double costPrice;
}
