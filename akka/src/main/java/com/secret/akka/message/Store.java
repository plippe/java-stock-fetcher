package com.secret.akka.message;

import java.util.List;
import java.io.Serializable;

import com.secret.model.marketprovider.MarketProviderData;

public class Store {
    public static class SaveMarketProviderData implements Serializable {
        public final MarketProviderData value;
        public SaveMarketProviderData(MarketProviderData value) {
            this.value = value;
        }
    }

    public static class SaveListMarketProviderData implements Serializable {
        public final List<MarketProviderData> list;
        public SaveListMarketProviderData(List<MarketProviderData> list) {
            this.list = list;
        }
    }
}
