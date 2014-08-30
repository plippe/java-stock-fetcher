package com.secret.common;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListUtilsTest extends TestCase {
  public ListUtilsTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( ListUtilsTest.class ); }

  public void testSlidding() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    
    List<List<Integer>> slide1 = ListUtils.sliding(list, 2);
    List<List<Integer>> slide1ShouldBe = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4), Arrays.asList(4, 5), Arrays.asList(5));
    assertEquals(slide1, slide1ShouldBe);
       
    List<List<Integer>> slide2 = ListUtils.sliding(list, 2, 2);
    List<List<Integer>> slide2ShouldBe = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5));
    assertEquals(slide2, slide2ShouldBe);
    
    List<List<Integer>> slide3 = ListUtils.sliding(list, 2, list.size());
    List<List<Integer>> slide3ShouldBe = Arrays.asList(Arrays.asList(1, 2));
    assertEquals(slide3, slide3ShouldBe);
    
    List<List<Integer>> slide4 = ListUtils.sliding(list, list.size(), list.size());
    List<List<Integer>> slide4ShouldBe = Arrays.asList(Arrays.asList(1, 2, 3, 4, 5));
    assertEquals(slide4, slide4ShouldBe);
            
    assertEquals(ListUtils.sliding(list, 0), new ArrayList());
    assertEquals(ListUtils.sliding(list, -1), new ArrayList());
    assertEquals(ListUtils.sliding(list, 2, 0), new ArrayList());
    assertEquals(ListUtils.sliding(list, 2, -1), new ArrayList());
  }
  
  public void testMkString() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    
    assertEquals(ListUtils.mkString(Arrays.asList()), "");
    assertEquals(ListUtils.mkString(Arrays.asList(), " "), "");
    assertEquals(ListUtils.mkString(Arrays.asList(), "start", " ", "end"), "startend");
    
    assertEquals(ListUtils.mkString(list), "12345");
    assertEquals(ListUtils.mkString(list, " "), "1 2 3 4 5");
    assertEquals(ListUtils.mkString(list, " , "), "1 , 2 , 3 , 4 , 5");
    assertEquals(ListUtils.mkString(list, "start", " ", "end"), "start1 2 3 4 5end");
  }
}
