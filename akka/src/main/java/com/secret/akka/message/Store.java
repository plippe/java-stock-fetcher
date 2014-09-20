package com.secret.akka.message;

import com.secret.model.products.Product;
import java.io.Serializable;

public class Store {
    public static class SaveProduct implements Serializable {
        public final Product productToSave;
        public SaveProduct(Product productToSave) {
            this.productToSave = productToSave;
        }
    }
    public static class SaveProductInDB implements Serializable {
        public final Product productToSave;
        public SaveProductInDB(Product productToSave) {
            this.productToSave = productToSave;
        }
    }

    public static class GetProduct implements Serializable {
        public final String seekedProductId;
        public GetProduct(String seekedProductId) {
            this.seekedProductId = seekedProductId;
        }
    }

    public static class GetProductFromDB implements Serializable {
        public final String seekedProductId;
        public GetProductFromDB(String seekedProductId) {
            this.seekedProductId = seekedProductId;
        }
    }

    public static class ProductResponse implements Serializable {
        public final Product product;
        public ProductResponse(Product product) {
            this.product = product;
        }
    }
}
