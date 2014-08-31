package com.secret.marketprovider.yahoo;

import com.secret.marketprovider.generic.Provider;

public class YahooProvider extends Provider {
  public YahooProvider() {
    super(
      new YahooFetcher(), 
      new YahooParser());
  }
}
