package dsa.leetcode.kuiperBelt;

public class FindTheMaximumSumOfNodeValues {

  /**
   * https://leetcode.com/problems/find-the-maximum-sum-of-node-values/submissions/
   * The approach involves iterating over the nums array and for each number, calculating the XOR with k.
   * It keeps track of the maximum sum we can achieve, and adjusts based on the count of nums where XOR operation increases the value.
   * This solution leverages the property of XOR to increase the total sum selectively.
   * 
   * Three crucial observations:
   * 1. a^k^k = a^(k^k) = a
   * 2. Due to #1 and tree like nature of setup, we can select any two nodes and perform XOR operation on them.
   * It propagates from node#1 to top, then from top to node#2. Hence all xor operations nullify due to #1 on 
   * the path except the nodes#1 and #2.
   * 3. In case of even number of XOR operations(i.e. a^k > a), we can find the 
   * => sum = sum(max(a, a^k))
   * 4. In case of odd number of XOR operations i.e. for one of the nodes we need to subtract the value. 
   * => except = min(abs(a-a^k))
   * 
   * p.s. in case of arbitrary graphs, the approach will be wildly different.
   *
   * TC: O(n) SC: O(1)
   * #array #greedy #bit-manipulation #tree #min-tracking #math #hard
   */
  public long maximumValueSum(int[] nums, int k, int[][] edges) {
    long sum = 0, except = Integer.MAX_VALUE, cnt = 0;
    for(int num: nums){
      int newnum = num^k;
      int val = Math.max(newnum, num);
      sum += val;
      if(newnum > num) cnt++;
      except = Math.min(except, Math.abs(num-(num^k)));
    }
    return cnt%2==0?sum:(sum-except);
  }
}