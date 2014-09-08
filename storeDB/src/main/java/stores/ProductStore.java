package stores;

import com.secret.app.products.Product;

public interface ProductStore
{
    Product loadProduct(final String productId);

    void saveProduct(final Product product);
}
