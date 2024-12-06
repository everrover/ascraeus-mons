package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class LongestSquareStreakInAnArray {
  
  /**
   * https://leetcode.com/problems/longest-square-streak-in-an-array/submissions/
   * 
   * Use a HashSet to store unique elements from the array and sort them. For each element, check if its square exists in the set.
   * Continuously check and count until no more squares are found for the sequence.
   * Return the maximum count found, otherwise, return -1 if no valid square streaks are found.
   * 
   * TC: O(n log n) SC: O(n)
   * #array #hash-table #sorting #dynamic-programming #medium
   */
  
  public int longestSquareStreak(int[] nums) {
    int res = -1;
    Set<Integer> set = new HashSet<>();
    for (int num : nums) set.add(num);
    List<Integer> arr = new ArrayList<>(set);
    Collections.sort(arr);
    int cnt = 0;
    for (int curr: arr) {
      if(curr == 0) continue;
      cnt = 0;
      while (set.contains(curr)) {
        set.remove(curr);
        curr = curr * curr;
        cnt++;
      }
      res = Math.max(res, cnt);
    }
    return res > 1 ? res : -1;
  }
}