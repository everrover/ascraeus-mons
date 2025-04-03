package dsa.leetcode.VallesMarineris;

public class MaximumValueOfAnOrderedTriplet {
  /**
   * https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/description/?envType=daily-question&envId=2025-04-02
   *
   * Use three nested loops to find all the triplets of indices (i, j, k) such that i < j < k. 
   * Calculate the value (nums[i] - nums[j]) * nums[k] and track the maximum value.
   * Return the maximum value found or 0 if all such triplets have a negative value.
   *
   * TC: O(n^3) SC: O(1)
   * #array #brute-force #easy #relevant-for-an-interview
   */
  
  public long maximumTripletValue(int[] nums) {
    long res = 0L;
    for(int i=0; i<nums.length; i++){
      for(int j=i+1; j<nums.length; j++){ 
        for(int k=j+1; k<nums.length; k++){ 
          res = Math.max(
            res, 
            (0L+nums[i]-nums[j])*nums[k] 
          ); 
        } 
      } 
    }
    return res;
  }

  // Greedy with 2 Loops
  // Fix k and then j, track max value of nums[i] for i < j
  // TC: O(n^2) SC: O(1)

  public long maximumTripletValue2(int[] nums) {
    int n = nums.length;
    long res = 0;
    for (int k = 2; k < n; k++) {
      int maxPrefix = nums[0];
      for (int j = 1; j < k; j++) {
        res = Math.max(res, (long) (maxPrefix - nums[j]) * nums[k]);
        maxPrefix = Math.max(maxPrefix, nums[j]);
      }
    }
    return res;
  }

  // 3. Greedy + Prefix/Suffix Arrays
  // Precompute max left and right for each j
  // SC: O(n) TC: O(n)
  public int maxTripletValuePS(int[] nums) {
    int n = nums.length;
    int[] leftMax = new int[n], rightMax = new int[n];
    leftMax[0] = nums[0];
    for (int i = 1; i < n; i++) leftMax[i] = Math.max(leftMax[i - 1], nums[i - 1]);
    rightMax[n - 1] = 0;
    for (int i = n - 2; i >= 0; i--) rightMax[i] = Math.max(rightMax[i + 1], nums[i + 1]);
    
    int res = 0;
    for (int j = 1; j < n - 1; j++)
      res = Math.max(res, (leftMax[j] - nums[j]) * rightMax[j]);
    return res;
  }

  // Track max of nums[i] and (nums[i] - nums[j]) during a single pass.
  // SC: O(1) TC: O(n)
  public int maxTripletValueGreedy(int[] nums) {
    int res = 0, imax = nums[0], dmax = Integer.MIN_VALUE;
    for (int k = 0; k < nums.length; k++) {
      res = Math.max(res, dmax * nums[k]);
      dmax = Math.max(dmax, imax - nums[k]); // max diff computed, always with imax
      imax = Math.max(imax, nums[k]);
    }
    return res;
  }


}