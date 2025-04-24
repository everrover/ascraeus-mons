package dsa.leetcode.JupitersGreatStorm;

import java.util.Stack;

public class MakeArrayNonDecreasing {

  /**
   * https://leetcode.com/problems/make-array-non-decreasing/description/
   *
   * Iterate through the array and use a stack to simulate removing subarrays 
   * and replace them with their maximums until a non-decreasing array is achieved.
   * 
   * Since we aren't using any other value other than top, we can use just that as well...
   *
   * TC: O(n), where n is the length of the array
   * SC: O(n), for the stack storage
   * #array #stack #greedy #monotonic stack #medium
   */

  public int maximumPossibleSize(int[] nums) {
    Stack<Integer> ms = new Stack<>();
    ms.push(0);
    int res = 0;
    for(int i=0; i<nums.length; i++){
      if(ms.peek() <= nums[i]){
        ms.push(nums[i]);
      }
    }
    return ms.size()-1;
  }
}