package com.secret.model.products;

import com.secret.model.enums.Currency;
import com.secret.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Product implements Serializable
{
    private static final long serialVersionUID = 7140440194431055196L;

    private final String code;
    private final String name;
    private final ProductType productType;
    private final Currency currency;
    private final String marketId;
}
