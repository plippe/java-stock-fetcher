package com.secret.marketprovider.generic;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProviderTest extends TestCase {
  public ProviderTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( ProviderTest.class ); }

  public void testGetSymbols() {
    // Give 0 symbols get 0 calls
    // Give less than max symbols get 1 call
    // Give 3 times max symbols get 3 calls
    // If error occurs, retry 3 times
    
    assertTrue( true );
  }
}
