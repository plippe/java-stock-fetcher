CREATE TABLE marketproviderdata (
  id SERIAL,
  symbol VARCHAR(255),
  value REAL NOT NULL,
  time TIMESTAMP NOT NULL);
