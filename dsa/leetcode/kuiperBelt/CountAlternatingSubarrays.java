package dsa.leetcode.kuiperBelt;

public class CountAlternatingSubarrays {

  /**
   * https://leetcode.com/problems/count-alternating-subarrays/
   * This method iterates through the binary array to find alternating subarrays.
   * It uses a two-pointer approach to find the length of each alternating subarray
   * and calculates the total count based on these lengths.
   *
   * TC: O(n) SC: O(1)
   * #array #math #dynamic-programming #medium
   */

  public long countAlternatingSubarrays(int[] nums) {
    long res = 0;
    int i=0;
    while(i<nums.length){
      int j=i+1;
      long len=1;
      while(j<nums.length){
        if(nums[j] == nums[j-1]) break;
        len++;j++;
      }
      res+=((len+1)*len/2);i=j;
    }
    return res;
  }
}