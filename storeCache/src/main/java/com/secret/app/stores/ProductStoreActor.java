package com.secret.app.stores;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.secret.model.products.Product;
import javax.annotation.PostConstruct;
import static com.secret.akka.message.Store.GetProduct;
import static com.secret.akka.message.Store.ProductResponse;
import static com.secret.akka.message.Store.SaveProduct;

public class ProductStoreActor extends UntypedActor {

    private ProductStore productStore;

    @PostConstruct
    public void init()
    {
        this.productStore = new ProductStoreImpl();
    }

    @Override
    public void onReceive(Object message) throws Exception
    {
        switch(message.getClass().getName())
        {
            case "com.secret.akka.message.Store$SaveProduct":
                onSaveProduct((SaveProduct) message, getSender());
                break;
            case "com.secret.akka.message.Store$GetProduct":
                onGetProduct((GetProduct) message, getSender());
                break;
            default:
                System.out.printf("Unknown '%s' \n", message.getClass().getName());
                unhandled(message);
        }
    }

    private void onGetProduct(GetProduct message, ActorRef sender) {
        Product retrievedProduct = this.productStore.getProduct(message.seekedProductId);
        sender.tell(new ProductResponse(retrievedProduct), getSelf());
    }

    private void onSaveProduct(SaveProduct message, ActorRef sender) {
        this.productStore.saveProduct(message.productToSave);
    }
}
