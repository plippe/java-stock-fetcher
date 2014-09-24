package com.secret.common;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DateUtilsTest extends TestCase {
  public DateUtilsTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( DateUtilsTest.class ); }

  public void testToJoda() {
    Long timestamp = 1420070400L;
    java.sql.Date value = new java.sql.Date(timestamp);

    java.util.Date update = DateUtils.toJoda(value);
    java.util.Date updateShouldBe = new java.util.Date(timestamp);
    assertEquals(update, updateShouldBe);
  }

  public void testToSql() {
    Long timestamp = 1420070400L;
    java.util.Date value = new java.util.Date(timestamp);

    java.sql.Date update = DateUtils.toSql(value);
    java.sql.Date updateShouldBe = new java.sql.Date(timestamp);

    assertEquals(update, updateShouldBe);
  }
}
