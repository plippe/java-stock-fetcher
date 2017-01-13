package com.secret.marketprovider.yahoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.NumberFormatException;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.secret.model.marketprovider.MarketProviderData;
import com.secret.marketprovider.generic.Parser;
import com.secret.marketprovider.exceptions.InvalidResponseException;

class YahooParser extends Parser {
  private Double extractPercent(String str) throws NumberFormatException {
    Pattern p = Pattern.compile("([-]?[0-9]+\\.[0-9]+)%$");
    Matcher m = p.matcher(str);
    
    try {
      m.find();
      return Double.parseDouble(m.group(1));   
    } catch(Exception e) {
      throw new NumberFormatException();
    }
  }

  public List<MarketProviderData> parse(String content) throws Exception {
    if(content == "") { throw new InvalidResponseException("Content is empty"); }
    
    CSVFormat format = CSVFormat.DEFAULT;
    CSVParser parser = CSVParser.parse(content, format);
    List<CSVRecord> list = parser.getRecords();
    
    List<MarketProviderData> result = new ArrayList();
    
    Date time = new Date();
    for(CSVRecord el: list) {
      try {
        String symbol = el.get(0).toUpperCase();
        Double value = Double.parseDouble(el.get(1));

        result.add(new MarketProviderData(symbol, value, time));
      } catch(NumberFormatException e) { continue; }
    }
    
	  return result;
	}
}
