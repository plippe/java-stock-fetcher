package com.secret.model.products;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductComponent
{
    private final Product component;
    private final double ponderation;
}
