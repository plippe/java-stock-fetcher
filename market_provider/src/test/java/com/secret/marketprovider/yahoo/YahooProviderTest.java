package com.secret.marketprovider.yahoo;

import java.util.Arrays;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.secret.model.marketprovider.MarketProviderData;

public class YahooProviderTest extends TestCase {
  public YahooProviderTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( YahooProviderTest.class ); }
  
  public void testProvider() {
    try {
      YahooProvider yahoo = new YahooProvider();
      List<String> list = Arrays.asList("goog", "aapl", "msft", "sne", "ntdoy");
      List<MarketProviderData> response = yahoo.get(list);
      
      assertEquals("Fetch 5 items should return 5 items", response.size(), list.size());
      assertEquals("Item 1 symbol must match", response.get(0).getSymbol(), "GOOG");
      assertEquals("Item 2 symbol must match", response.get(1).getSymbol(), "AAPL");
      assertEquals("Item 3 symbol must match", response.get(2).getSymbol(), "MSFT");
      assertEquals("Item 4 symbol must match", response.get(3).getSymbol(), "SNE");
      assertEquals("Item 5 symbol must match", response.get(4).getSymbol(), "NTDOY");
    } catch(Exception e) {
      fail("Should not raised errors in normal conditions");    
    }
  }
}
