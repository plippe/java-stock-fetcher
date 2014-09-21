package com.secret.app;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.secret.app.Message.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class App {
  public static void main( String[] args ) {
      JDBCConnector connector = new JDBCConnector();
      connector.connect();
    /*
    final ActorSystem system = ActorSystem.create("system-name");
    final ActorRef server = system.actorOf(Props.create(Server.class));

    server.tell(new Start(), ActorRef.noSender());
    */
  }
}
