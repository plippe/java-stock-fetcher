package com.secret.model.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductComposition {

    private final Product product;
    private final Date valueDate;
    private final List<ProductComponent> components;
}
