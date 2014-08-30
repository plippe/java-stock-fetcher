package com.secret.marketprovider.yahoo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class YahooParserTest extends TestCase {
  public YahooParserTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( YahooParserTest.class ); }

  public void testParse() {
    // Give 0 lines get 0 results
    // Give 5 lines get 5 results
    // Give 3 valid and 2 invalid lines raise alarm
    assertTrue( true );
  }
}
