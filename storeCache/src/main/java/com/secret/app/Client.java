package com.secret.app;

import akka.actor.UntypedActor;
import akka.actor.ActorRef;

import com.secret.app.Message.*;

public class Client extends UntypedActor {
  private void onPing(Ping message) {
    System.out.printf("Client - Ping \n"); 
  }

  private void onPingFrom(PingFrom message) {
    System.out.printf("Client - Pingged by '%s' \n", message.from);
  }
  
  private void onQuestion(Question message, ActorRef sender) {
    switch (message.what) {
      case "A":
        System.out.println("Client - Asked A - Gave Z"); 
        sender.tell(new Response("Z"), getSelf());
        break;
      case "B":
        System.out.println("Client - Asked B - Gave Y"); 
        sender.tell(new Response("Y"), getSelf());
        break;
      case "C":
        System.out.println("Client - Asked C - Gave X"); 
        sender.tell(new Response("X"), getSelf());
        break;
    }
  }

  public void onReceive(Object message) {
    switch (message.getClass().getName()) {
      case "com.secret.app.Message$Ping":
        onPing((Ping) message);
        break;
      case "com.secret.app.Message$PingFrom":
        onPingFrom((PingFrom) message);
        break;        
      case "com.secret.app.Message$Question":
        onQuestion((Question) message, getSender());
        break;
      default:
        System.out.printf("Unknown '%s' \n", message.getClass().getName());
        unhandled(message);        
    }
  }
}
