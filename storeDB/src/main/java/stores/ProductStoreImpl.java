package stores;

import com.google.common.base.Preconditions;
import com.secret.app.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductStoreImpl implements ProductStore {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product loadProduct(final String productId)
    {
        Preconditions.checkNotNull(productId, "Param productId must not be null");
        return productRepository.getOne(productId);
    }

    @Override
    public void saveProduct(final Product product)
    {
        Preconditions.checkNotNull(product, "Param product must not be null");
        Preconditions.checkNotNull(product.getId(), "Param Id must not be null");
        Preconditions.checkNotNull(product.getProductType(), "Param product type must not be null");

        this.productRepository.save(product);
    }
}
