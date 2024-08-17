package dsa.leetcode.jovianMoonOrbit;

// Necessary imports
import java.util.Arrays;

/*
 * https://leetcode.com/problems/find-the-power-of-k-size-subarrays-ii/
 *
 * Given an array of integers nums and a positive integer k, 
 * find the power of all subarrays of nums of size k.
 * 
 * The power of an array is defined as:
 * - Its maximum element if all elements are consecutive and sorted in ascending order.
 * - -1 otherwise.
 * 
 * Simply iterate through the array to find the length of the longest subarray 
 * ending at each index that is consecutive and sorted. Then, use this to determine 
 * if each k-size subarray is valid.
 * 
 * TC: O(n) SC: O(n)
 * #array #sliding-window #medium
 */

public class FindThePowerOfKSizeSubarraysII {

  public int[] resultsArray(int[] nums, int k) {
    int []mark = new int[nums.length];
    int []res = new int[nums.length-k+1];
    Arrays.fill(mark, 1); // Initialize mark array with 1
    
    // Mark lengths of consecutive sorted subarrays
    for(int i=1; i<nums.length; i++){
      if(nums[i]==nums[i-1]+1)
        mark[i] = mark[i-1]+1;
    }
    
    // Determine power of each k-size subarray
    for(int i=k-1; i<nums.length; i++){
      if(mark[i] >= k)
        res[i-k+1]=nums[i]; // Maximum element of valid subarray
      else
        res[i-k+1] = -1; // Invalid subarray
    }
    return res;
  }
}