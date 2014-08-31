package com.secret.marketprovider.generic;

import java.util.List;

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
  
  public List<MarketDataResponse> get(List<String> symbols) throws Exception {
    String originalContent = fetcher.fetch(symbols);
    return parser.parse(originalContent);
  }
}
