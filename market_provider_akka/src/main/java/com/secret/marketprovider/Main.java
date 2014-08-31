package com.secret.marketprovider;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Main {
  public static void main(String[] args) {      
    final Config conf = ConfigFactory.load();
    final String systemName = conf.getString("my-akka.system.name"); 
    final String actorName = conf.getString("my-akka.actor.name"); 
    
    final ActorSystem system = ActorSystem.create(systemName);
    system.actorOf(Props.create(Server.class).withRouter(new FromConfig()), actorName);
  }
}
