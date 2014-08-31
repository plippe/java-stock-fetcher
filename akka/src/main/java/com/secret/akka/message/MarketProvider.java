package com.secret.akka;

import java.io.Serializable;
import java.util.List;

import com.secret.model.providers.MarketDataResponse;

public class MarketProvider {
  public static class Request implements Serializable {
    public final List<String> list;
    public Request(List<String> list) {
        this.list = list;
    }
  }
  
  public static class Response implements Serializable {
    public final List<MarketDataResponse> list;
    public Response(List<MarketDataResponse> list) {
        this.list = list;
    }
  }
}
