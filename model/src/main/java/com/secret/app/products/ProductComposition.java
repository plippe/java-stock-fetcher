package com.secret.app.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductComposition {

    private Product product;
    private Date valueDate;
    private List<ProductComponent> components;
}
