package stores;

import static com.secret.app.products.Product.ProductKey;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.secret.app.enums.ProductType;
import com.secret.app.products.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Entity
public class ProductStoreImpl implements ProductStore {

    @Autowired
    private ProductRepository productRepository;


    private LoadingCache<ProductKey, Product> productCache = null;

    @PostConstruct
    public void init()
    {
        Integer itemDuration = 6;
        TimeUnit itemDurationTimeUnit = TimeUnit.HOURS;

        this.productCache = CacheBuilder.newBuilder()
                .expireAfterWrite(itemDuration,itemDurationTimeUnit)
                .build( new CacheLoader<ProductKey, Product>() {
                            public Product load(ProductKey key){
                                return innerGetProduct(key);
                            }
                        });
    }

    private Product innerGetProduct(ProductKey key) {
        //Get en base
        return productRepository.findOne(key);
    }

    @Override
    public Product getProduct(String productId, ProductType productType)
    {
        Preconditions.checkNotNull(productId, "Param productId must not be null");
        Preconditions.checkNotNull(productType, "Param productType must not be null");

        return getProduct(new ProductKey(productId,productType));
    }

    @Override
    public Product getProduct(ProductKey key)
    {
        Preconditions.checkNotNull(key, "Param key must not be null");
        Preconditions.checkNotNull(key.getId(), "Param key Id must not be null");
        Preconditions.checkNotNull(key.getProductType(), "Param key product type must not be null");

        Product product = null;

        try {
            product = this.productCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void saveProduct(Product product)
    {
        Preconditions.checkNotNull(product, "Param product must not be null");
        Preconditions.checkNotNull(product.getId(), "Param Id must not be null");
        Preconditions.checkNotNull(product.getProductType(), "Param product type must not be null");

        this.productCache.put(product.getProductKey(), product);
        this.productRepository.save(product);
    }
}
