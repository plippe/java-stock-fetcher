package stores;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.secret.model.products.Product;
import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ProductStoreImpl implements ProductStore
{

    private LoadingCache<String, Product> productCache = null;

    @PostConstruct
    public void init()
    {
        Integer itemDuration = 6;
        TimeUnit itemDurationTimeUnit = TimeUnit.HOURS;

        this.productCache = CacheBuilder.newBuilder()
                .expireAfterWrite(itemDuration,itemDurationTimeUnit)
                .build(new CacheLoader<String, Product>() {
                            public Product load(String key){
                                return innerGetProduct(key);
                            }
                        });
    }

    private Product innerGetProduct(String key) {
        //Get en base
        //Envoie akka
        return null;
    }

    @Override
    public Product getProduct(String productId)
    {
        Preconditions.checkNotNull(productId, "Param key must not be null");

        Product product = null;

        try {
            product = this.productCache.get(productId);
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

        this.productCache.put(product.getId(), product);
    }
}
