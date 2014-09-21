package com.secret.app.stores;

import com.secret.model.providers.MarketDataResponse;

/**
 * Created by User on 21/09/2014.
 */
public interface MarketDataStore {
    MarketDataResponse getMarketData(String productId);

    void saveMarketData(MarketDataResponse marketDataResponse);
}
