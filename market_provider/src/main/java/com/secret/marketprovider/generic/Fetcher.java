package com.secret.marketprovider.generic;

import java.util.List;

abstract public class Fetcher {
  final public Integer maxSymbolsPerRequest;
  
  protected Fetcher(Integer maxSymbolsPerRequest) {
    this.maxSymbolsPerRequest = maxSymbolsPerRequest;
  }
  

  abstract public String fetch(List<String> symbols) throws Exception;
}
