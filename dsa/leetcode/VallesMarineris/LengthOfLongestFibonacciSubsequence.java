package dsa.leetcode.VallesMarineris;

import java.util.*;

public class LengthOfLongestFibonacciSubsequence {

  /**
   * https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/description/?envType=daily-question&envId=2025-02-27
   *
   * The solution uses dynamic programming with a hash map to find previous indices of array elements.
   * For each pair of numbers, it checks if the difference exists as a prior number in the sequence.
   * If it does, it extends the length by 1. If not, it starts a new sequence with these two numbers.
   * 
   * For an index, 0, we don't have any previous numbers to check for, so res=1;
   * For an index, 1, we have an initial pair of numbers, so res=2;
   * For an index, 2, we have a possible value to check for at `0`, so we need to insert `0` indexed element
   * into the map.
   * Similarly for index 3 and onwards, we insert the element at index `idx-2` into the map to find the prevPrev
   * using the `curr` and `prev` values. 
   * i.e. `prevPrev = curr - prev` if it exists in the map and `prevPrev < prev`.
   * 
   * Second check is to ensure we pick an element that's present before the `prev` element.
   * 
   * TC: O(n^2) SC: O(n^2)
   * #array #hash-table #dynamic-programming #medium
   */

  public int lenLongestFibSubseq(int[] arr) {
    if(arr.length <= 2) return 0;
    int [][]maxlen = new int[arr.length][arr.length];
    int res = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for(int curr=0; curr<arr.length; curr++){
      map.put(arr[curr], curr);
      for(int prev=0; prev<curr; prev++){
        int diff = arr[curr]-arr[prev];
        int prevPrev = map.getOrDefault(diff, -1);
        maxlen[prev][curr] = (prevPrev >= 0 && diff < arr[prev]) ? (maxlen[prevPrev][prev]+1) : 2;
        res = Math.max(res, maxlen[prev][curr]);
      }
    }
    return res > 2 ? res : 0;
  }
}