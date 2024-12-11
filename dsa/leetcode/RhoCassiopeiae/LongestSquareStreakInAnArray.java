package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class LongestSquareStreakInAnArray {
  
  /**
   * https://leetcode.com/problems/longest-square-streak-in-an-array/submissions/
   * 
   * Use a HashSet to store unique elements from the array and sort them. 
   * For each element, check if its square exists in the set.
   * Continuously check and count until no more squares are found for the sequence.
   * 
   * It's essentially dynamic programming without explicit state traversal using DFS. 
   * ❗️ Could've done that as well though...
   * 
   * Binary search could've been used to find the square of a number repeatedly, with logic
   * similar to as in Sieve of Eratosthenes. on sorted array, ofcourse.
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
  // public int longestSquareStreak(int[] nums) {
  //   Arrays.sort(nums);
  //   int res = -1;
  //   Map<Integer, Integer> streaks = new HashMap<>();
  //   for(int num: nums){
  //     int sqroot = (int) Math.sqrt(num);
  //     if(sqroot * sqroot == num && streaks.containsKey(sqroot)){
  //       streaks.put(num, streaks.get(sqroot) + 1);
  //     }else{
  //       streaks.put(num, 1);
  //     }
  //   }
  //   for(int e: streaks.values()){
  //     res = Math.max(res, e);
  //   }
    
  //   return res==1?-1:res;
  // }
}