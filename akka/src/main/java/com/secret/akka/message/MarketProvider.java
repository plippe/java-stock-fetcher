package com.secret.akka.message;

import java.io.Serializable;
import java.util.List;

import com.secret.model.marketprovider.MarketProviderData;

public class MarketProvider {
  public static class Request implements Serializable {
    public final List<String> list;
    public Request(List<String> list) {
        this.list = list;
    }
  }
  
  public static class Response implements Serializable {
    public final List<MarketProviderData> list;
    public Response(List<MarketProviderData> list) {
        this.list = list;
    }
  }
}
