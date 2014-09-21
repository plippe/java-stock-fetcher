package com.secret.app;

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
