package com.secret.common;

public class DateUtils {
  public static java.util.Date toJoda(java.sql.Date value) {
    Long time = value.getTime();
    return new java.util.Date(time);
  }
  
  public static java.sql.Date toSql(java.util.Date value) {
    Long time = value.getTime();
    return new java.sql.Date(time);
  }
}
