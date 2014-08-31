package com.secret.marketprovider.yahoo;

import java.util.List;
import java.net.URL;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.IOUtils;

import com.secret.marketprovider.generic.Fetcher;
import com.secret.marketprovider.exceptions.RequestSizeException;

class YahooFetcher extends Fetcher {
  public YahooFetcher() {
    // Yahoo limits 200 symbols per call
    super(200); 
  }
  
  // https://code.google.com/p/yahoo-finance-managed/wiki/csvQuotesDownload
	public String fetch(List<String> symbols) throws Exception {
	  Integer size = symbols.size();
	  if(size == 0) { throw new RequestSizeException("Request has no symbols"); }
	  if(size > getMaxSymbolsPerRequest()) { throw new RequestSizeException("Request has too many symbols"); }
	  
	  String hostAndPath = "http://download.finance.yahoo.com/d/quotes.csv";
    String defaultQueryString = "?f=s0l1c6k2";
    String queryString = "&s=" + StringUtils.join(symbols, ",");
    URL url = new URL(hostAndPath + defaultQueryString + queryString);
    InputStream in = url.openStream();
    
    String result = "";
    try {
      result = IOUtils.toString(in);
    } finally {
      IOUtils.closeQuietly(in);
    }
    
    return result.trim();
	}
}
