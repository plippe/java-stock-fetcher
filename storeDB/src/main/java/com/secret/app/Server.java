package com.secret.app;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.secret.akka.message.Store;
import com.secret.app.Message.Response;
import com.secret.model.enums.Currency;
import com.secret.model.enums.ProductType;
import com.secret.model.products.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Server extends UntypedActor {
  private void onStart() {
    System.out.println("Server starting");

      final ActorRef client = getContext().actorOf(Props.create(DBProductStoreActor.class));
      //Product productToSave = new Equity("IDtest", ProductType.EQUITY, Currency.EUR,"marketIdTest","NameTest");

      Product productToSave = new Product("IDtest", ProductType.EQUITY, Currency.EUR,"marketIdTest");
      client.tell(new Store.SaveProductInDB(productToSave), ActorRef.noSender());
  }

  private void onStop() {
    System.out.println("Server stopping");
    getContext().system().shutdown();
  }
  
  private void onResponse(Response message) {
    System.out.printf("Server response: %s \n", message.what);
  }

  public void onReceive(Object message) {
    switch (message.getClass().getName()) {
      case "com.secret.app.Message$Start":
        onStart();
        break;
      case "com.secret.app.Message$Stop":
        onStop();
        break;
      case "com.secret.app.Message$Response":
        onResponse((Response) message);
        break;
      default:
        System.out.printf("Unknown '%s' \n", message.getClass().getName());
        unhandled(message);
    }
  }
}
