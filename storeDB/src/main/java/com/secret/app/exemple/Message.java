package com.secret.app.exemple;

import java.io.Serializable;

public class Message {
  public static class Start implements Serializable {}

  public static class Stop implements Serializable {}

  public static class Ping implements Serializable {}
  
  public static class PingFrom implements Serializable {
    public final String from;
    public PingFrom(String from) {
        this.from = from;
    }
  }
    
  public static class Question implements Serializable {
    public final String what;
    public Question(String what) {
        this.what = what;
    }
  }
  
  public static class Response implements Serializable {
    public final String what;
    public Response(String what) {
        this.what = what;
    }
  }
}
