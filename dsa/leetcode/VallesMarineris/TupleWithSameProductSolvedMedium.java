package dsa.leetcode.VallesMarineris;

import java.util.*;

public class TupleWithSameProductSolvedMedium {

  /**
   * https://leetcode.com/problems/tuple-with-same-product/description/?envType=daily-question&envId=2025-02-06
   *
   * Count the frequency of each product of two distinct numbers in the array. 
   * Then, calculate the permutations with these products.
   * 
   * Since all numbers are distinct, the pairs we select by indexes are by themselves distinct.
   * i.e. automatically are a != b != c != d.
   * 
   * TC: O(n^2) SC: O(n)
   * #array #hash-table #counting #medium
   */

  public int tupleSameProduct(int[] nums) {
    HashMap<Integer, Integer> hm = new HashMap<>();
    int res = 0;
    for(int i=nums.length-1; i>=1; i--) {  
      for(int j=i-1; j>=0; j--) {  
        int m = nums[i]*nums[j];
        hm.put(m, hm.getOrDefault(m, 0) + 1);
      }
    }
    for(int prod: hm.values()) {
      res += 8*(prod * (prod-1) / 2);  // Calculate permutations
    }
    return res;
  }
}