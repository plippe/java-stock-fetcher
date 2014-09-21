package com.secret.store.db;

import java.sql.SQLException;
import java.sql.ResultSet;

abstract class Table<T> {
  abstract protected T rowToModel(ResultSet row) throws SQLException;
}
