package com.secret.marketprovider.yahoo;

import java.util.List;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.secret.marketprovider.generic.Parser;

class YahooParser extends Parser {
  public String parse(String content) throws Exception {
    CSVFormat format = CSVFormat.DEFAULT;
    CSVParser parser = CSVParser.parse(content, format);
    List<CSVRecord> list = parser.getRecords();
    
    String result = "";
    for(CSVRecord el: list) {
      String symbol = el.get(0);
      result += symbol;
    }
    
	  return result;
	}
}
