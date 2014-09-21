package com.secret.store.db;

import java.sql.*;
import java.util.Optional;

import com.secret.model.providers.MarketDataResponse;

public class MarketDataResponseTable extends Table<MarketDataResponse> {
  private final Connection conn;
  public MarketDataResponseTable(Connection conn) {
    this.conn = conn;
  }
  
  protected MarketDataResponse rowToModel(ResultSet row) throws SQLException {
    final String id = row.getString("id");
    final Date valueDate = row.getDate("date");
    final Double last = row.getDouble("last");
    final Double change = row.getDouble("change");
    final Double changePercent = row.getDouble("change_percent");
    
    return new MarketDataResponse(id, valueDate, last, change, changePercent);
  }
  
  public Optional<MarketDataResponse> getBySymbol(String symbol) {   
    try {
      String query = "SELECT * FROM marketdataresponse WHERE id = ?;";
      PreparedStatement ps = conn.prepareStatement(query);
      ps.setString(1, symbol);
      
      ResultSet rs = ps.executeQuery();
      if(rs.next()) { return Optional.of(rowToModel(rs)); }
      else { return Optional.empty(); }
    } catch (Exception e) {
      // TODO - log
      return Optional.empty();
    }
  }
  
  public Boolean save(MarketDataResponse model) { 
    try {
      String query = "" +
        "INSERT INTO marketdataresponse(id, date, last, change, change_percent) " +
        "VALUES (?, ?, ?, ?, ?);";
      PreparedStatement ps = conn.prepareStatement(query);
      ps.setString(1, model.getId());
      ps.setDate(2, new Date(model.getValueDate().getTime()));
      ps.setDouble(3, model.getLast());
      ps.setDouble(4, model.getChange());
      ps.setDouble(5, model.getChangePercent());
      
      int inserted = ps.executeUpdate();
      return inserted == 1;
    } catch (Exception e) {
      // TODO - log
      return false;
    }
  
  }
}
