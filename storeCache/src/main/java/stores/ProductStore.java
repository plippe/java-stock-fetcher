package stores;

import com.secret.app.products.Product;

public interface ProductStore
{
    Product getProduct(final String productId);

    void saveProduct(final Product product);
}
