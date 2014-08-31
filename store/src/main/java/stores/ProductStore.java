package stores;

import com.secret.model.enums.ProductType;
import com.secret.model.products.Product;
import static com.secret.model.products.Product.ProductKey;

public interface ProductStore
{
    Product getProduct(final String productId, final ProductType productType);

    Product getProduct(final ProductKey key);

    void saveProduct(final Product product);
}
