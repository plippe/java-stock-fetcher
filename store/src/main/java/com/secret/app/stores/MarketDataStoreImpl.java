package com.secret.app.stores;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.secret.model.providers.MarketDataResponse;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MarketDataStoreImpl implements MarketDataStore {

    private LoadingCache<String, MarketDataResponse> marketDataCache = null;

    @PostConstruct
    public void init()
    {
        Integer itemDuration = 6;
        TimeUnit itemDurationTimeUnit = TimeUnit.HOURS;

        this.marketDataCache = CacheBuilder.newBuilder()
                .expireAfterWrite(itemDuration,itemDurationTimeUnit)
                .build(new CacheLoader<String, MarketDataResponse>() {
                    public MarketDataResponse load(String key){
                        return innerGetMarketData(key);
                    }
                });
    }

    private MarketDataResponse innerGetMarketData(String key) {
        //todo: get product in db
        return null;
    }

    @Override
    public MarketDataResponse getMarketData(String productId)
    {
        Preconditions.checkNotNull(productId, "Param key must not be null");

        MarketDataResponse marketDataResponse = null;

        try {
            marketDataResponse = this.marketDataCache.get(productId);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return marketDataResponse;
    }

    @Override
    public void saveMarketData(MarketDataResponse marketDataResponse)
    {
        Preconditions.checkNotNull(marketDataResponse, "Param product must not be null");

        this.marketDataCache.put(marketDataResponse.getId(), marketDataResponse);
        //todo: async save en base
    }
}
