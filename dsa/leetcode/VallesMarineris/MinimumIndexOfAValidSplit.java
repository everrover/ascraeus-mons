package dsa.leetcode.VallesMarineris;

import java.util.*;

public class MinimumIndexOfAValidSplit {

  /**
   * https://leetcode.com/problems/minimum-index-of-a-valid-split/description/?envType=daily-question&envId=2025-03-27
   *
   * The method finds the minimum index to split the array such that both halves
   * have the same dominant element. It calculates the frequency of the dominant element
   * on both sides of the split and checks validity based on conditions.
   *
   * TC: O(n) SC: O(1)
   * #array #hashtable #sorting #medium
   */

  public int minimumIndex(List<Integer> nums) {
    int tot = cnt; // Total occurrences of the dominant element
    cnt = 0; // Reset count for tracking dominant frequency

    for(int i = 0; i < nums.size(); i++) {
      if(cand == nums.get(i)) cnt++; // Increment count if current element is dominant

      int fc = cnt, fp = i + 1 - cnt; // Frequency and position left of split
      int sc = tot - cnt, sp = nums.size() - i - 1 - tot + cnt; // Frequency and position right of split

      if(fc > fp && sc > sp) return i; // Check if both sides are valid
    }

    return -1; // Return -1 if no valid split exists
  }
}