package com.secret.akka.remote;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import com.typesafe.config.Config;

public class RemoteActor {
  private ActorRef ref;
      
  public RemoteActor(ActorContext context, String path) {
    ref = context.actorFor(path);
  }
    
  public void tell(Object msg, ActorRef sender) {
    ref.tell(msg, sender);
  }
}
