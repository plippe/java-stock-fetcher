package com.secret.store;

import java.sql.Connection;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import com.secret.common.JdbcConnection;

public class Main {
  public static void main(String[] args) {      
    final Config conf = ConfigFactory.load();

    final Connection conn = JdbcConnection.get(conf);

    final String systemName = conf.getString("my-akka.system.name");     
    final String marketProviderDataName = conf.getString("my-akka.actors.marketproviderdata.name"); 
    
    final ActorSystem system = ActorSystem.create(systemName);
    system.actorOf(Props.create(ActorMarketProviderData.class, conn), marketProviderDataName);
  }
}
