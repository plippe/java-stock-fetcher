package com.secret.marketprovider.yahoo;

import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.secret.marketprovider.exceptions.InvalidResponseException;
import com.secret.model.marketprovider.MarketProviderData;

public class YahooParserTest extends TestCase {
  public YahooParserTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( YahooParserTest.class ); }
  
  public void testEmptyParse() {
    YahooParser parser = new YahooParser();
    try {
      parser.parse("");
      fail("Should have raised a InvalidResponseException");
    } catch(InvalidResponseException e) {
    } catch(Exception e) { fail("Should have raised a InvalidResponseException"); }
  }

  public void testValidParse() {
    YahooParser parser = new YahooParser();
    try {
      String content = 
        "\"A\",10.20\n" +
        "\"B\",20.30\n" +
        "\"C\",30.40";      
      
      List<MarketProviderData> response = parser.parse(content);
      assertEquals("Fetch 3 items should return 3 items", response.size(), 3);
      
      assertEquals("Item 1 symbol must match", response.get(0).symbol, "A");
      assertEquals("Item 1 value must match", response.get(0).value, 10.20);
      
      assertEquals("Item 2 symbol must match", response.get(1).symbol, "B");
      assertEquals("Item 2 value must match", response.get(1).value, 20.30);
      
      assertEquals("Item 3 symbol must match", response.get(2).symbol, "C");
      assertEquals("Item 3 value must match", response.get(2).value, 30.40);
    } catch(Exception e) {
      fail("Should not raised errors in normal conditions");
    }
  }
  
  public void testInvalidParse() {
    YahooParser parser = new YahooParser();
    try {
      String content = 
        "\"A\",\"A\"\n" + // Unknown symbol will result in requested symbol over all fields
        "\"B\",\"Not valid double\"\n" +
        "\"C\",10.20";
      
      List<MarketProviderData> response = parser.parse(content);
      assertEquals("Fetch 3 items with 2 invalid ones should return 1 item", response.size(), 1);
      
      assertEquals("Item 1 symbol must match", response.get(0).symbol, "C");
      assertEquals("Item 1 value must match", response.get(0).value, 10.20);
    } catch(Exception e) {
      fail("Should not raised errors in normal conditions");
    }
  }
}
