package com.secret.marketprovider.generic;

import java.util.List;
import java.util.ArrayList;

import com.secret.common.ListUtils;
import com.secret.app.providers.MarketDataResponse;

abstract public class Provider {
  private Fetcher fetcher;
  private Parser parser;

  protected Provider(Fetcher fetcher, Parser parser) {
    this.fetcher = fetcher;
    this.parser = parser;
  }

  private Integer getMaxSymbolsPerRequest() {
    return fetcher.getMaxSymbolsPerRequest();
  }
  
  public List<MarketDataResponse> get(List<String> symbols) {
    List<MarketDataResponse> result = new ArrayList();
    
    Integer maxSymbolsPerRequest = getMaxSymbolsPerRequest();
    List<List<String>> symbolsDividedForRequests = ListUtils.sliding(
      symbols, 
      maxSymbolsPerRequest, 
      maxSymbolsPerRequest);
    
    for(List<String> symbolsForOneRequest : symbolsDividedForRequests){
      try {
        String originalContent = fetcher.fetch(symbols);
        List<MarketDataResponse> convertedContent = parser.parse(originalContent);
      
        result.addAll(convertedContent);
      } catch(Exception e) {
        System.err.println("Exeption thrown: " + e.getMessage());
      }
    }

    return result;
  }
}
