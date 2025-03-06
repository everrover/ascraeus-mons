package dsa.leetcode.VallesMarineris;

class TransformArrayByParity {
  /**
   * https://leetcode.com/problems/transform-array-by-parity/description/
   *
   * Replace each even number in the array with 0 and each odd number with 1.
   * Count the even numbers and fill the beginning of the result with 0s and the remaining with 1s.
   *
   * TC: O(n), where n is the length of the input array
   * SC: O(1)
   * #array #sorting #counting #easy
   */
  public int[] transformArray(int[] nums) {
    int even = 0;
    // Count the number of even numbers
    for(int num: nums) 
      if(num % 2 == 0) 
        even++;
    
    // Replace with 0 or 1 based on the even count
    for(int i = 0; i < nums.length; i++) {
      nums[i] = (even-- > 0) ? 0 : 1;
    }
    return nums;
  }
}