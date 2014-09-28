package com.secret.store.cache;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.secret.common.Optional;
import com.secret.model.marketprovider.MarketProviderData;

public class CacheMarketProviderDataStoreTest extends TestCase {
  public CacheMarketProviderDataStoreTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( CacheMarketProviderDataStoreTest.class ); }

  public void testFindAndSave() {
    try {
      String symbol = "a";

      CacheMarketProviderDataStore store = new CacheMarketProviderDataStore();
      assertFalse(store.findBySymbol(symbol).isPresent());

      MarketProviderData value1 = new MarketProviderData(symbol, 1.0, new java.util.Date(100, 1, 1, 1, 1));
      store.save(value1);
      Optional<MarketProviderData> found1 = store.findBySymbol(symbol);
      assertTrue(found1.isPresent());
      assertEquals(found1.get().getValue(), 1.0);

      MarketProviderData value2 = new MarketProviderData(symbol, 2.0, new java.util.Date(100, 2, 2, 2, 2));
      store.save(value2);
      Optional<MarketProviderData> found2 = store.findBySymbol(symbol);
      assertTrue(found2.isPresent());
      assertEquals(found2.get().getValue(), 2.0);
    } catch(Exception e) { fail("Should not raised errors in normal conditions"); }
  }
}
