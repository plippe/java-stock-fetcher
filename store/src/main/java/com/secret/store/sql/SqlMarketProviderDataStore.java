package com.secret.store.sql;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.secret.common.DateUtils;
import com.secret.store.generic.MarketProviderDataStore;
import com.secret.model.marketprovider.MarketProviderData;

public class SqlMarketProviderDataStore extends MarketProviderDataStore {
  private final Connection conn;
  public SqlMarketProviderDataStore(Connection conn) {
    this.conn = conn;
  }

  public MarketProviderData rowToModel(ResultSet row) throws SQLException {
    final Long id = row.getLong("id");
    final String symbol = row.getString("symbol");
    final Double value = row.getDouble("value");
    final Date time = row.getDate("time");
    
    return new MarketProviderData(id, symbol, value, time);
  }

  public Optional<MarketProviderData> findBySymbol(String symbol) throws Exception {
    String query = "" +
      "SELECT id, symbol, value, time " +
      "FROM marketproviderdata " +
      "WHERE symbol = ? " +
      "ORDER BY time DESC " +
      "LIMIT 1";

    PreparedStatement ps = conn.prepareStatement(query);
    ps.setString(1, symbol);

    ResultSet rs = ps.executeQuery();
    Optional<MarketProviderData> result = Optional.empty();
    if(rs.next()){ 
      MarketProviderData value = rowToModel(rs);
      result = Optional.of(value); 
    }

    return result;
  }

  public void save(MarketProviderData value) throws Exception {
    String query = "" +
      "INSERT INTO marketproviderdata (symbol, value, time) " +
      "VALUES (?, ?, ?);";

    PreparedStatement ps = conn.prepareStatement(query);
    ps.setString(1, value.getSymbol());
    ps.setDouble(2, value.getValue());
    ps.setDate(3, DateUtils.toSql(value.getTime()));

    ps.execute();
  }
}