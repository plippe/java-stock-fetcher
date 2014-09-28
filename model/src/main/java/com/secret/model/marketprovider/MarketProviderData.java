package com.secret.model.marketprovider;

import java.io.Serializable;
import java.util.Date;

import com.secret.common.Optional;

public class MarketProviderData implements Serializable {
  private final Optional<Long> id;  
  private final String symbol; 
  private final Double value;
  private final Date time;
  
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
  
  public Optional<Long> getId() { return id; }
  public String getSymbol() { return symbol; }
  public Double getValue() { return value; }
  public Date getTime() { return time; }
}
