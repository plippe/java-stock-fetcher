package com.secret.model.providers;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

public class MarketProviderData implements Serializable {
  private final Optional<Integer> id;  
  private final String symbol; 
  private final Double value;
  private final Date time;
  
  public MarketProviderData(Integer id, String symbol, Double value, Date time) {
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
  
  public Optional<Integer> getId() { return id; }
  public String getSymbol() { return symbol; }
  public Double getValue() { return value; }
  public Date getTime() { return time; }
}
