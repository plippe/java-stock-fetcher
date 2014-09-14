package com.secret.marketprovider;

import java.util.List;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import com.secret.akka.message.MarketProvider;
import com.secret.common.ListUtils;
import com.secret.marketprovider.generic.Provider;
import com.secret.marketprovider.yahoo.YahooProvider;
import com.secret.model.providers.MarketDataResponse;

public class Server extends UntypedActor {
  final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  final Provider provider = new YahooProvider();

  private void onNormalRequest(MarketProvider.Request request, ActorRef sender) {    
    try {
      List<MarketDataResponse> content = provider.get(request.list);
      MarketProvider.Response response = new MarketProvider.Response(content);
      
      sender.tell(response, ActorRef.noSender());
    } catch(Exception e) {
      log.error("Exeption thrown: {}", e.getMessage());
    }
  }
  
  private void onLargeRequest(MarketProvider.Request request, ActorRef sender) {
    List<List<String>> smallerLists = ListUtils.sliding(
      request.list, 
      provider.getMaxSymbolsPerRequest(), 
      provider.getMaxSymbolsPerRequest());
      
    for(List<String> list : smallerLists){
      getSelf().tell(new MarketProvider.Request(list), sender);
    }
  }
  
  private void onRequest(MarketProvider.Request request, ActorRef sender) {
    if(request.list.size() <= provider.getMaxSymbolsPerRequest()) {
      onNormalRequest(request, sender);
    } else {
      onLargeRequest(request, sender);    
    }
  }

  public void onReceive(Object message) {    
    switch (message.getClass().getName()) {
      case "com.secret.akka.message.MarketProvider$Request":
        onRequest((MarketProvider.Request) message, getSender());
        break;
      default:
        log.error("Unknown message: {}", message.getClass().getName());
        unhandled(message);        
    }
  }
}
