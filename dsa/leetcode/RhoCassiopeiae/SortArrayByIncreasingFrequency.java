package dsa.leetcode.RhoCassiopeiae;

import java.util.Arrays;

public class Solution {
  /**
   * https://leetcode.com/problems/sort-array-by-increasing-frequency/
   * 
   * Sort the array elements by frequency in increasing order. Handle ties by sorting elements with the same frequency in decreasing order.
   *  
   * TC: O(n log n) - sorting step
   * SC: O(1) - in-place sorting
   * 
   * #array #hash-table #sorting #easy
   */
  
  public int[] frequencySort(int[] nums) {
    int cnt[][] = new int[201][2];
    for(int i=0; i<201; i++){
      cnt[i][0] = i-100;
    }
    for(int num:nums) cnt[num+100][1]++;
    Arrays.sort(
      cnt,
      (a,b) -> a[1]==b[1] ? (b[0]-a[0]) : (a[1]-b[1])
    );
    int idx = 0;
    for(int []freq: cnt){
      while(freq[1] > 0) {
        // Place frequent numbers in the result based on frequency
        --freq[1];
        nums[idx++] = freq[0];
      }
    }
    return nums;
  }
}