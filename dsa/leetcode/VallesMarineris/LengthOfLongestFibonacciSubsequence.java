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