CREATE TABLE MarketDataResponse (
  id VARCHAR(255) PRIMARY KEY,
  date TIMESTAMP NOT NULL,
  last REAL NOT NULL,
  change REAL NOT NULL,
  change_percent REAL NOT NULL);
