package com.secret.model.marketprovider;

import java.io.Serializable;
import java.util.Date;

import com.secret.common.Optional;

public class MarketProviderData implements Serializable {
  public final Optional<Long> id;  
  public final String symbol; 
  public final Double value;
  public final Date time;
  
  public MarketProviderData(Long id, String symbol, Double value, Date time) {
    this.id = Optional.of(id);
    this.symbol = symbol;
    this.value = value;
    this.time = time;
  }
  
  public MarketProviderData(String symbol, Double value, Date time) {
    this.id = Optional.empty();
    this.symbol = symbol;
    this.value = value;
    this.time = time;
  }
}
