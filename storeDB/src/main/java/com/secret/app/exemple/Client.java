package com.secret.app.exemple;

import akka.actor.UntypedActor;
import akka.actor.ActorRef;

import com.secret.app.exemple.Message.*;

public class Client extends UntypedActor {
  private void onPing(Message.Ping message) {
    System.out.printf("Client - Ping \n"); 
  }

  private void onPingFrom(Message.PingFrom message) {
    System.out.printf("Client - Pingged by '%s' \n", message.from);
  }
  
  private void onQuestion(Message.Question message, ActorRef sender) {
    switch (message.what) {
      case "A":
        System.out.println("Client - Asked A - Gave Z"); 
        sender.tell(new Message.Response("Z"), getSelf());
        break;
      case "B":
        System.out.println("Client - Asked B - Gave Y"); 
        sender.tell(new Message.Response("Y"), getSelf());
        break;
      case "C":
        System.out.println("Client - Asked C - Gave X"); 
        sender.tell(new Message.Response("X"), getSelf());
        break;
    }
  }

  public void onReceive(Object message) {
    switch (message.getClass().getName()) {
      case "com.secret.app.exemple.Message$Ping":
        onPing((Message.Ping) message);
        break;
      case "com.secret.app.exemple.Message$PingFrom":
        onPingFrom((Message.PingFrom) message);
        break;        
      case "com.secret.app.exemple.Message$Question":
        onQuestion((Message.Question) message, getSender());
        break;
      default:
        System.out.printf("Unknown '%s' \n", message.getClass().getName());
        unhandled(message);        
    }
  }
}
