package com.secret.marketprovider.yahoo;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.secret.marketprovider.exceptions.RequestSizeException;

public class YahooFetcherTest extends TestCase {
  public YahooFetcherTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( YahooFetcherTest.class ); }

  public void testEmptyFetch() {
    YahooFetcher fetcher = new YahooFetcher();
    try {
      fetcher.fetch(new ArrayList());
      fail("Should have raised a RequestSizeException");
    } catch(RequestSizeException e) {
    } catch(Exception e) { fail("Should have raised a RequestSizeException"); }
  }
  
  public void testFullFetch() {
    YahooFetcher fetcher = new YahooFetcher();
    try {
      List<String> list = new ArrayList();
      for(Integer i = 0; i < fetcher.getMaxSymbolsPerRequest(); i++) { list.add(i.toString()); }
      list.add("OneToMuch");
      fetcher.fetch(list);
      fail("Should have raised a RequestSizeException");
    } catch(RequestSizeException e) {
    } catch(Exception e) { fail("Should have raised a RequestSizeException"); }
  }
  
  public void testFetch() {
    YahooFetcher fetcher = new YahooFetcher();
    try {
      List<String> list = Arrays.asList("a", "b", "c", "d", "e");
      String fetch = fetcher.fetch(list);
      assertEquals("Fetch 5 items should return 5 items", StringUtils.countMatches(fetch, "\n") + 1, list.size());
    } catch(Exception e) {
      fail("Should not raised errors in normal conditions");
    }
  }
}
