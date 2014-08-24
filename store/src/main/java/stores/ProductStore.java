package stores;

import com.secret.app.enums.ProductType;
import com.secret.app.products.Product;
import static com.secret.app.products.Product.ProductKey;

public interface ProductStore
{
    Product getProduct(final String productId, final ProductType productType);

    Product getProduct(final ProductKey key);

    void saveProduct(final Product product);
}
