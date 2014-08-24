package com.secret.app;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.Props;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import java.util.concurrent.TimeUnit;

import com.secret.app.Message.*;

public class Server extends UntypedActor {
  private void onStart() {
    System.out.println("Server starting"); 
    
    final ActorRef client = getContext().actorOf(Props.create(Client.class));

    System.out.println("App sending 3 Ping");
    for(int i = 0; i < 3; i++) {
      client.tell(new Ping(), ActorRef.noSender());
    }
    
    System.out.println("App sending 3 PingFrom");
    client.tell(new PingFrom("A"), ActorRef.noSender());
    client.tell(new PingFrom("B"), ActorRef.noSender());
    client.tell(new PingFrom("C"), ActorRef.noSender());
    
    System.out.println("App schedule a Ping every second");
    FiniteDuration first = Duration.Zero();
    FiniteDuration interval = Duration.create(1, TimeUnit.SECONDS);
    getContext().system().scheduler().schedule(first, interval, client, new Ping(), getContext().dispatcher(), null);
        
    System.out.println("App asking to client");
    client.tell(new Question("A"), getSelf());
    client.tell(new Question("B"), getSelf());
    client.tell(new Question("C"), getSelf());
    
    System.out.println("App schedule shudown in 10 seconds");
    getContext().system().scheduler().scheduleOnce(
      Duration.create(10, TimeUnit.SECONDS), getSelf(), new Stop(), getContext().dispatcher(), null);
  }

  private void onStop() {
    System.out.println("Server stopping");
    getContext().system().shutdown();
  }
  
  private void onResponse(Response message) {
    System.out.printf("Server response: %s \n", message.what);
  }

  public void onReceive(Object message) {
    switch (message.getClass().getName()) {
      case "com.secret.app.Message$Start":
        onStart();
        break;
      case "com.secret.app.Message$Stop":
        onStop();
        break;
      case "com.secret.app.Message$Response":
        onResponse((Response) message);
        break;
      default:
        System.out.printf("Unknown '%s' \n", message.getClass().getName());
        unhandled(message);
    }
  }
}
