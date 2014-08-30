package com.secret.common;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ListUtils {
  public static <T> List<List<T>> sliding(List<T> list, Integer size) {
    return sliding(list, size, 1);
  }
  
  public static <T> List<List<T>> sliding(List<T> list, Integer size, Integer step) {
    if(size <= 0 || step <= 0) { 
      return new ArrayList(); 
    }
    
    Integer fromIndex = 0;
    Integer lastIndex = list.size();
    
    List<List<T>> result = new ArrayList();
    while(fromIndex < lastIndex) {
      Integer toIndex = Math.min(fromIndex + size, lastIndex);
      List<T> subList = list.subList(fromIndex, toIndex);
      result.add(subList);
      
      fromIndex += step;
    }
    
    return result;
  }
  
  public static <T> String mkString(List<T> list) { return mkString(list, "", "", ""); }
  
  public static <T> String mkString(List<T> list, String separator) { 
    return mkString(list, "", separator, "");
  }
  
  public static <T> String mkString(List<T> list, String start, String separator, String end) {
    return start + StringUtils.join(list, separator) + end;
  }
}
