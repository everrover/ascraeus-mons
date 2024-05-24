package dsa.leetcode.kuiperBelt;

/**
 * https://leetcode.com/problems/special-array-ii/
 * This solution introduces a prefix sum approach to identify if a subarray is special.
 * A special subarray is one where every pair of adjacent elements has different parity.
 * The solution iterates through the given 'nums' array, calculating a running parity difference using a prefix array 'p'.
 * For each query, the solution checks if the subarray defined by the query maintains the special property by comparing
 * the prefix sum values at the boundaries of the subarray.
 *
 * TC: O(n) for creating the prefix array, O(m) for processing m queries. Overall O(n+m).
 * SC: O(n) for the prefix array.
 *
 * Tags: #prefix-sum #array #medium
 */
public class SpecialArrayII {

  public boolean[] isArraySpecial(int[] nums, int[][] queries) {
    int []p = new int[nums.length+1];
    p[0] = 0;
    for(int i=1; i<nums.length; i++){
      if(nums[i]%2!= nums[i-1]%2)
        p[i] = p[i-1];
      else p[i] = p[i-1]+1;
    }
    boolean []res = new boolean[queries.length];
    int i=0;
    for(int []q: queries){
      if((p[q[1]] - p[q[0]]) == 0) res[i] = true;
       i++;
    }
    return res;
  }
}