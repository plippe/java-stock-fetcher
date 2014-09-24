package com.secret.marketprovider.generic;

import java.util.List;

import com.secret.model.marketprovider.MarketProviderData;

abstract public class Parser {
	abstract public List<MarketProviderData> parse(String content) throws Exception;
}
