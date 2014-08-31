package com.secret.marketprovider.generic;

import java.util.List;

import com.secret.model.providers.MarketDataResponse;

abstract public class Parser {
	abstract public List<MarketDataResponse> parse(String content) throws Exception;
}
