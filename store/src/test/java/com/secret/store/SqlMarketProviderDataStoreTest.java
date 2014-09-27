package com.secret.store.sql;

import java.sql.*;
import java.util.Optional;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.secret.store.JdbcConnection;
import com.secret.model.marketprovider.MarketProviderData;

public class SqlMarketProviderDataStoreTest extends TestCase {
  public SqlMarketProviderDataStoreTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( SqlMarketProviderDataStoreTest.class ); }

  Connection conn = JdbcConnection.get();

  void insertDummyRow(Integer id, String symbol, Double value, Date time) {    
    try {
      String query = "INSERT INTO marketproviderdata (id, symbol, value, time) VALUES (?, ?, ?, ?)";
      PreparedStatement ps = conn.prepareStatement(query);
      ps.setLong(1, id);
      ps.setString(2, symbol);
      ps.setDouble(3, value);
      ps.setDate(4, time);
      ps.execute();
    } catch(Exception e) { 
      System.out.println(e.getMessage());
    }
  }

  void insertDummy() {
    insertDummyRow(1, "a", 1.0, new Date(1));
    insertDummyRow(2, "b", 2.0, new Date(2));
    insertDummyRow(3, "c", 3.0, new Date(3));   
    insertDummyRow(4, "d", 4.0, new Date(4)); 
    insertDummyRow(5, "e", 5.0, new Date(5));  
  }

  public void setUp() {
    try {
      String query = "DELETE FROM marketproviderdata";
      Statement st = conn.createStatement();
      st.executeUpdate(query);
    } catch(Exception e) { }
  }

  public void testAll() {
    try {  
      insertDummy();

      SqlMarketProviderDataStore store = new SqlMarketProviderDataStore(conn);
      assertEquals(store.all().size(), 5);
    } catch(Exception e) { fail("Should not raised errors in normal conditions"); }
  }

  public void testFindById() {
    try {
      insertDummy();
      
      SqlMarketProviderDataStore store = new SqlMarketProviderDataStore(conn);
      Optional<MarketProviderData> value = store.findById(3L);

      assertTrue(value.isPresent());
      assertEquals(value.get().getSymbol(), "c");
    } catch(Exception e) { fail("Should not raised errors in normal conditions"); }
  }

  public void testInsert() {
    try {
      MarketProviderData value = new MarketProviderData("A", 10.20, new Date(0));
      SqlMarketProviderDataStore store = new SqlMarketProviderDataStore(conn);
      store.save(value);

      assertEquals(store.all().size(), 1);
    } catch(Exception e) { fail("Should not raised errors in normal conditions"); }
  }

  public void testUpdate() {
    try {
      insertDummy();
      
      SqlMarketProviderDataStore store = new SqlMarketProviderDataStore(conn);
      store.save(new MarketProviderData(3L, "c (updated)", 3.0, new Date(3)));

      assertEquals(store.all().size(), 5);

      Optional<MarketProviderData> value = store.findById(3L);      
      assertTrue(value.isPresent());
      assertEquals(value.get().getSymbol(), "c (updated)");
    } catch(Exception e) { fail("Should not raised errors in normal conditions"); }
  }
}
