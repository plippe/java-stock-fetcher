package com.secret.dispatcher;

import java.util.List;
import akka.actor.UntypedActor;
import akka.actor.Scheduler;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import com.secret.akka.remote.RemoteActor;
import com.secret.akka.message.Common.Ping;
import com.secret.akka.message.MarketProvider;
import com.secret.akka.message.Store.SaveMarketProviderData;
import com.secret.model.marketprovider.MarketProviderData;

public class ActorServer extends UntypedActor {
  final LoggingAdapter log = Logging.getLogger(getContext().system(), this);  
  
  final Config conf = ConfigFactory.load();
  final String marketProviderPath = conf.getString("my-akka.remote.marketprovider");
  final RemoteActor marketProvider = new RemoteActor(getContext(), marketProviderPath);

  final String storePath = conf.getString("my-akka.remote.store");
  final RemoteActor store = new RemoteActor(getContext(), storePath);
  
  public void preStart() {    
    log.info("preStart");
    
    final FiniteDuration first = Duration.Zero();
    final FiniteDuration interval = Duration.create(1, TimeUnit.MINUTES);
    
    final Scheduler scheduler =  getContext().system().scheduler();
    scheduler.schedule(first, interval, getSelf(), new Ping(), getContext().dispatcher(), null);
  }
  
  private void onRequestMarketData() {
    log.info("onRequestMarketData");

    final List<String> symbols = conf.getStringList("symbols");
    final MarketProvider.Request request = new MarketProvider.Request(symbols);
    marketProvider.tell(request, getSelf());
  }
  
  private void onMarketProviderResponse(MarketProvider.Response response) {
    log.info("onMarketProviderResponse");

    for (MarketProviderData value : response.list) {
      store.tell(new SaveMarketProviderData(value), getSelf());
    }
  }

  public void onReceive(Object message) {    
    switch (message.getClass().getName()) {
      case "com.secret.akka.message.Common$Ping":
        onRequestMarketData();
        break;
      case "com.secret.akka.message.MarketProvider$Response":
        onMarketProviderResponse((MarketProvider.Response) message);
        break;
      default:
        log.error("Unknown message: {}", message.getClass().getName());
        unhandled(message);        
    }
  }
}
