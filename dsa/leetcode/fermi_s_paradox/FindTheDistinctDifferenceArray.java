package dsa.leetcode.fermi_s_paradox;

import java.util.HashSet;
import java.util.Set;

public class Solution {
  /**
   * https://leetcode.com/problems/find-the-distinct-difference-array/description/
   *
   * The function calculates the distinct difference array by iterating through the
   * array twice. First, it calculates the number of distinct elements in the suffix.
   * Then, it calculates the number of distinct elements in the prefix and finds their difference.
   * 
   * TC: O(n^2) SC: O(n)
   * #array #hash-table #easy
   */

  public int[] distinctDifferenceArray(int[] nums) {
    Set<Integer> s = new HashSet<>();
    int[] a = new int[nums.length];
    
    // Calculate distinct elements in suffix
    for (int i = nums.length - 1; i >= 0; i--) {
      a[i] = s.size();
      s.add(nums[i]);
    }
    
    s.clear();
    
    // Calculate difference with prefix
    for (int i = 0; i < nums.length; i++) {
      s.add(nums[i]);
      a[i] = s.size() - a[i];
    }
    
    return a;
  }
}