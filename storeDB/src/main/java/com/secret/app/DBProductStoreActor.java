package com.secret.app;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.google.common.base.Preconditions;
import com.secret.akka.message.Store;
import com.secret.app.old.RepoWrapper;
import com.secret.model.products.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.secret.akka.message.Store.GetProductFromDB;
import static com.secret.akka.message.Store.SaveProductInDB;

public class DBProductStoreActor extends UntypedActor {

    private Connection conn;

    public DBProductStoreActor(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public void onReceive(Object message) throws Exception {

        switch (message.getClass().getName()) {
            case "com.secret.akka.message.Store$SaveProductInDB":
                onSaveProductInDB((SaveProductInDB) message, getSender());
                break;
            case "com.secret.akka.message.Store$GetProductFromDB":
                onGetProductFromDB((GetProductFromDB) message, getSender());
                break;
            default:
                System.out.printf("Unknown '%s' \n", message.getClass().getName());
                unhandled(message);
        }
    }

    private void onGetProductFromDB(GetProductFromDB message, ActorRef sender) {
        Preconditions.checkNotNull(message.seekedProductId, "Param productId must not be null");

        try {
            Statement stmt = conn.createStatement();
            String sqlQuerry = "SELECT id, first, last, age FROM Employees";

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Product retrievedProduct =null;
        sender.tell(new Store.ProductResponse(retrievedProduct), getSelf());
    }

    private void onSaveProductInDB(SaveProductInDB message, ActorRef sender) {

        Preconditions.checkNotNull(message.productToSave, "Param product must not be null");
        Preconditions.checkNotNull(message.productToSave.getId(), "Param Id must not be null");
        Preconditions.checkNotNull(message.productToSave.getProductType(), "Param product type must not be null");

        //this.repo.save(message.productToSave);
    }
}
