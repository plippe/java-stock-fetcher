package com.secret.marketprovider.yahoo;

import com.secret.marketprovider.generic.Provider;

class YahooProvider extends Provider {
  public YahooProvider() {
    super(
      new YahooFetcher(), 
      new YahooParser());
  }
}
