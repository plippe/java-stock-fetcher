package com.secret.app.stores.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.secret.akka.message.Store;
import com.secret.app.stores.MarketDataStore;
import com.secret.app.stores.MarketDataStoreImpl;
import com.secret.model.providers.MarketDataResponse;
import javax.annotation.PostConstruct;

public class MarketDataStoreActor  extends UntypedActor {

    private MarketDataStore marketDataStore;

    @PostConstruct
    public void init() {
        this.marketDataStore = new MarketDataStoreImpl();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        switch (message.getClass().getName()) {
            case "com.secret.akka.message.Store$SaveMarketData":
                onSaveSaveMarketData((Store.SaveMarketData) message, getSender());
                break;
            case "com.secret.akka.message.Store$GetMarketData":
                onGetProduct((Store.GetMarketData) message, getSender());
                break;
            default:
                System.out.printf("Unknown '%s' \n", message.getClass().getName());
                unhandled(message);
        }
    }

    private void onGetProduct(Store.GetMarketData message, ActorRef sender) {
        MarketDataResponse retrievedMarketData = this.marketDataStore.getMarketData(message.seekedProductId);
        sender.tell(new Store.MarketDataMessage(retrievedMarketData), getSelf());
    }

    private void onSaveSaveMarketData(Store.SaveMarketData message, ActorRef sender) {
        this.marketDataStore.saveMarketData(message.marketDataToSave);
    }
}