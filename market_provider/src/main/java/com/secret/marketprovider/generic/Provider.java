package com.secret.marketprovider.generic;

import java.util.List;

import com.secret.model.marketprovider.MarketProviderData;

abstract public class Provider {
  final private Fetcher fetcher;
  final private Parser parser;
  final public Integer maxSymbolsPerRequest;

  protected Provider(Fetcher fetcher, Parser parser) {
    this.fetcher = fetcher;
    this.parser = parser;

    maxSymbolsPerRequest = fetcher.maxSymbolsPerRequest;
  }
  
  public List<MarketProviderData> get(List<String> symbols) throws Exception {
    String originalContent = fetcher.fetch(symbols);
    return parser.parse(originalContent);
  }
}
