package com.secret.store;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.secret.akka.message.Store;
import com.secret.store.generic.MarketProviderDataStore;
import com.secret.store.cache.CacheMarketProviderDataStore;
import com.secret.store.sql.SqlMarketProviderDataStore;
import com.secret.model.marketprovider.MarketProviderData;

public class ActorMarketProviderData extends UntypedActor {
  final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

  final List<MarketProviderDataStore> stores;

  public ActorMarketProviderData(Connection conn) {
    stores = Arrays.asList(
      new CacheMarketProviderDataStore(),
      new SqlMarketProviderDataStore(conn));
  } 

  private void onSave(Store.SaveMarketProviderData save) {
    for(MarketProviderDataStore store : stores) {
      try { 
        store.save(save.value); 
      } catch(Exception e) {
        log.error("Unable to save: {}", save.value);
        log.error("Error message: {}", e.getMessage());
      }
    }
  }

  private void onSave(Store.SaveListMarketProviderData save, ActorRef sender) {    
    for(MarketProviderData value : save.list) {
      getSelf().tell(new Store.SaveMarketProviderData(value), sender);
    }
  }

  public void onReceive(Object message) {    
    switch (message.getClass().getName()) {
      case "com.secret.akka.message.Store$SaveMarketProviderData":
        onSave((Store.SaveMarketProviderData) message);
        break;
      case "com.secret.akka.message.Store$SaveListMarketProviderData":
        onSave((Store.SaveListMarketProviderData) message, getSender());
        break;
      default:
        log.error("Unknown message: {}", message.getClass().getName());
        unhandled(message);        
    }
  }
}
