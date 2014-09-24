package com.secret.akka.message;

import java.io.Serializable;

import com.secret.model.marketprovider.MarketProviderData;

public class Store {
    public static class SaveMarketProviderData implements Serializable {
        public final MarketProviderData value;
        public SaveMarketProviderData(MarketProviderData value) {
            this.value = value;
        }
    }

    public static class GetMarketProviderDataById implements Serializable {
        public final Integer value;
        public GetMarketProviderDataById(Integer value) {
            this.value = value;
        }
    }
}
