package com.secret.app.products;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProductComposition {

    private Product product;
    private Date valueDate;
    private List<ProductComponent> components;
}
