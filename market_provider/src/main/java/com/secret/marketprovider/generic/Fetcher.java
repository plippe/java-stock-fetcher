package com.secret.marketprovider.generic;

import java.util.List;

abstract public class Fetcher {
  private Integer maxSymbolsPerRequest;
  
  protected Fetcher(Integer maxSymbolsPerRequest) {
    this.maxSymbolsPerRequest = maxSymbolsPerRequest;
  }
  
  public Integer getMaxSymbolsPerRequest() {
    return maxSymbolsPerRequest;
  }  

	abstract public String fetch(List<String> symbols) throws Exception;
}
