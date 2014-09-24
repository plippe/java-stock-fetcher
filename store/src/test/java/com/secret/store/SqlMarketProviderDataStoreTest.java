package com.secret.store.sql;

import java.sql.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.secret.store.JdbcConnection;
import com.secret.model.marketprovider.MarketProviderData;

public class SqlMarketProviderDataStoreTest extends TestCase {
  public SqlMarketProviderDataStoreTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( SqlMarketProviderDataStoreTest.class ); }

  Connection conn = JdbcConnection.get();

  public void setUp() {
    try {
      String query = "DELETE FROM marketproviderdata";
      Statement st = conn.createStatement();
      st.executeUpdate(query);
    } catch(Exception e) { }
  }

  public void testAll() {
    // INSERT TWO
    // all should return two
  }

  public void testFindById() {
    // INSERT TWO
    // find one should be equal
    // find one that doesn exist should be empty
  }

  public void testInsert() {
    try {
      MarketProviderData value = new MarketProviderData("A", 10.20, new Date(0));
      SqlMarketProviderDataStore store = new SqlMarketProviderDataStore(conn);
      store.save(value);

      assertEquals(store.all().size(), 1);
    } catch(Exception e) {
      System.out.println(e.getMessage());
      fail("Should not raised errors in normal conditions");    
    }
  }

  public void testUpdate() {
    // INSERT TWO
    // Update one
    // find all
    // updated should be updated
    // other unchanged
  }
}
