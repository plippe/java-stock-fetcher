package com.secret.app.old;

import com.secret.app.old.ProductRepository;
import com.secret.model.products.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by User on 20/09/2014.
 */
public class RepoWrapper {

    private ProductRepository productRepository;

    public RepoWrapper()
    {
        System.out.println("Loading spring context");
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:META-INF/spring-context.xml");
        this.productRepository = (ProductRepository) ctx.getBean("productRepository");
    }

    public void save(Product productToSave)
    {
        productRepository.save(productToSave);
    }

    public Product getOne(String productId)
    {
        return productRepository.getOne(productId);
    }

}
