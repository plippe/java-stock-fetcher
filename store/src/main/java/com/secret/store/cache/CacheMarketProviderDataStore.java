package com.secret.store.cache;

import java.util.Map;
import java.util.HashMap;

import com.secret.common.Optional;
import com.secret.store.generic.MarketProviderDataStore;
import com.secret.model.marketprovider.MarketProviderData;

public class CacheMarketProviderDataStore extends MarketProviderDataStore {

  private Map<String, MarketProviderData> cache = new HashMap();

  public Optional<MarketProviderData> findBySymbol(String symbol) throws Exception {
    return cache.containsKey(symbol) ? Optional.of(cache.get(symbol)) : Optional.empty();
  }

  public void save(MarketProviderData value) throws Exception {
    cache.put(value.getSymbol(), value);
  }
}