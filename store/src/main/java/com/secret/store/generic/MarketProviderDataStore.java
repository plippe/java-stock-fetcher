package com.secret.store.generic;

import java.util.List;
import java.util.Optional;

import com.secret.model.marketprovider.MarketProviderData;

public abstract class MarketProviderDataStore {
  public abstract List<MarketProviderData> all() throws Exception;
  public abstract Optional<MarketProviderData> findById(Long id) throws Exception;

  public abstract void save(MarketProviderData value) throws Exception;
}
