package com.secret.marketprovider.yahoo;

import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.secret.marketprovider.exceptions.InvalidResponseException;
import com.secret.model.providers.MarketDataResponse;

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
        "\"A\",10.20,\"-1.0\",\"N/A - -1.00%\"\n" +
        "\"B\",20.30,\"+0.00\",\"N/A - +0.00%\"\n" +
        "\"C\",30.40,\"+1.00\",\"N/A - +1.00%\"";      
      
      List<MarketDataResponse> response = parser.parse(content);
      assertEquals("Fetch 3 items should return 3 items", response.size(), 3);
      
      assertEquals("Item 1 symbol must match", response.get(0).getId(), "A");
      assertEquals("Item 1 value must match", response.get(0).getLast(), 10.20);
      assertEquals("Item 1 change must match", response.get(0).getChange(), -1.00);
      assertEquals("Item 1 change percent must match", response.get(0).getChangePercent(), -1.00);
      
      assertEquals("Item 1 symbol must match", response.get(1).getId(), "B");
      assertEquals("Item 1 value must match", response.get(1).getLast(), 20.30);
      assertEquals("Item 1 change must match", response.get(1).getChange(), 0.00);
      assertEquals("Item 1 change percent must match", response.get(1).getChangePercent(), 0.00);
      
      assertEquals("Item 1 symbol must match", response.get(2).getId(), "C");
      assertEquals("Item 1 value must match", response.get(2).getLast(), 30.40);
      assertEquals("Item 1 change must match", response.get(2).getChange(), 1.00);
      assertEquals("Item 1 change percent must match", response.get(2).getChangePercent(), 1.00);
    } catch(Exception e) {
      fail("Should not raised errors in normal conditions");
    }
  }
  
  public void testInvalidParse() {
    YahooParser parser = new YahooParser();
    try {
      String content = 
        "\"A\",\"A\",\"A\",\"A\"\n" + // Unknown symbol will result in requested symbol over all fields
        "\"B\",\"Not valid double\",\"B\",\"B\"\n" +
        "\"C\",\"1\",\"Not valid double\",\"C\"\n" +
        "\"D\",\"1\",\"1\",\"Not valid double\"\n" +
        "\"E\",10.20,\"-1.0\",\"N/A - -1.00%\"";
      
      List<MarketDataResponse> response = parser.parse(content);
      assertEquals("Fetch 5 items with 4 invalid ones should return 1 item", response.size(), 1);
      
      assertEquals("Item 1 symbol must match", response.get(0).getId(), "E");
      assertEquals("Item 1 value must match", response.get(0).getLast(), 10.20);
      assertEquals("Item 1 change must match", response.get(0).getChange(), -1.00);
      assertEquals("Item 1 change percent must match", response.get(0).getChangePercent(), -1.00);
    } catch(Exception e) {
      fail("Should not raised errors in normal conditions");
    }
  }
}
