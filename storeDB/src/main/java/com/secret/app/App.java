package com.secret.app;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.secret.app.Message.*;
import com.secret.app.products.Product;

public class App {
  public static void main( String[] args ) {
    final ActorSystem system = ActorSystem.create("system-name");
    final ActorRef server = system.actorOf(Props.create(Server.class));

    server.tell(new Start(), ActorRef.noSender());
  }
}
