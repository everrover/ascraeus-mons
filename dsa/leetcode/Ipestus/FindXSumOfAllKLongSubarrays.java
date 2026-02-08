package dsa.leetcode.Ipestus;

import java.util.*;

public class FindXSumOfAllKLongSubarrays {

  /**
   * https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/description/
   *
   * Simple brute-force approach!!! Could've used a heap to optimize.
   *
   * TC: O(n * log(n)) SC: O(n)
   * #array #hash-table #sliding-window #heap #easy
   */

  public int[] findXSum(int[] nums, int k, int x) {
    int []res = new int[nums.length-k+1];
    Map<Integer, Integer> ts = new HashMap<>();
    for(int i=0; i<nums.length-k+1; i++){
      for(int j=i; j<i+k; j++){
        ts.put(nums[j], ts.getOrDefault(nums[j], 0)+1);
      }
      int [][]farr = new int[ts.size()][2];
      int z=0, currres=0;
      for(Map.Entry<Integer, Integer> me: ts.entrySet()){
        farr[z++] = new int[]{me.getKey(), me.getValue()};
      }
      Arrays.sort(farr, (a,b)->(b[1]==a[1])?(b[0]-a[0]):(b[1]-a[1]));
      for(z=0; z<Math.min(x, farr.length); z++) currres += farr[z][0]*farr[z][1];
      res[i] = currres;
      ts.clear();
    }
    return res;
  }
}