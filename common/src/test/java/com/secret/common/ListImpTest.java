package com.secret.common;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListImpTest extends TestCase {
  public ListImpTest( String testName ) { super( testName ); }
  public static Test suite() { return new TestSuite( ListImpTest.class ); }

  public void testSlidding() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    
    List<List<Integer>> slide1 = ListImp.sliding(list, 2);
    List<List<Integer>> slide1ShouldBe = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4), Arrays.asList(4, 5), Arrays.asList(5));
    assertEquals(slide1, slide1ShouldBe);
       
    List<List<Integer>> slide2 = ListImp.sliding(list, 2, 2);
    List<List<Integer>> slide2ShouldBe = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5));
    assertEquals(slide2, slide2ShouldBe);
    
    List<List<Integer>> slide3 = ListImp.sliding(list, 2, list.size());
    List<List<Integer>> slide3ShouldBe = Arrays.asList(Arrays.asList(1, 2));
    assertEquals(slide3, slide3ShouldBe);
    
    List<List<Integer>> slide4 = ListImp.sliding(list, list.size(), list.size());
    List<List<Integer>> slide4ShouldBe = Arrays.asList(Arrays.asList(1, 2, 3, 4, 5));
    assertEquals(slide4, slide4ShouldBe);
            
    assertEquals(ListImp.sliding(list, 0), new ArrayList());
    assertEquals(ListImp.sliding(list, -1), new ArrayList());
    assertEquals(ListImp.sliding(list, 2, 0), new ArrayList());
    assertEquals(ListImp.sliding(list, 2, -1), new ArrayList());
  }
}
