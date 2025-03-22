package dsa.leetcode.VallesMarineris;

import java.util.List;

public class ReachEndOfArrayWithMaxScore {
  /**
   * https://leetcode.com/problems/reach-end-of-array-with-max-score/
   *
   * Greedy choice:: From each index `i`, the optimal solution is to jump to the nearest index `j > i`
   * such that `nums[j] > nums[i]`, maximize the score `res` as per the problem statement.
   * 
   * Obs1: If any other index `j` is picked before the current value `j-i` is -ve and result would be worse.
   * Obs2: 0 is compulsorily included
   * Obs3: If any greater element is skipped, the answer will reduce for sure. Since we multiply with 
   * nums[i]. Do the additive math, and you'll see... 
   *
   * TC: O(n) SC: O(1)
   * #array #greedy #medium
   */
  public long findMaximumScore(List<Integer> nums) {
    long res = 0, sz = 0;
    int i = 0;
    int n = nums.size();
    while(i < n) {
      int j = i + 1;
      while(j < n) {
        if(nums.get(j) > nums.get(i)) break; // find the first larger element
        j++;
      }
      i = j;
      sz++;
      res += nums.get(i) * sz; // compute score with jump
    }
    return res;
  }
}