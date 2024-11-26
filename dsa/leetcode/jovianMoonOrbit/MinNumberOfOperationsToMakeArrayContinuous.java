package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class MinNumberOfOperationsToMakeArrayContinuous {
  /**
   * https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/submissions/
   *
   * 
   * Things to note:
   * - The array needs to be continuous, so if size is N, range is [x, x+N-1]
   * - Number of swaps needed is always < N
   * - Duplicates are to be swapped in any case, so we can fit them as we desire the decided range
   * 
   * The brute approach is to sort the array and then iterate over all possible ranges of size N
   * and find the minimum number of swaps needed to make the range continuous.
   * 
   * But the optimal solution always lies where orig_arr[N-1] >= x >=orig-arr[0]. Since before and after, 
   * atleast 1 extra swap will be needed to make the range continuous.
   * And picking any value between orig_arr[i] and orig_arr[i+1] also never results in optimal solution.
   * Since we've got better chances at including more elements in the range if we pick either.
   * 
   * Hence if we expand a subarray(sliding window) of size N around each element, we can find how
   * many elements are in the range [x, x+N-1] and how many are outside. The ones outside are the ones
   * which we need to swap and duplicates will fill the gaps.
   *
   * TC: O(n log n) SC: O(1)
   * #array #sliding-window #hard #sorting #tricky
   */

  public int minOperations(int[] nums) {
    Arrays.sort(nums);
    final int N = nums.length;
    int mk = 1;
    for(int i=1; i<nums.length; i++){
      if(nums[i] != nums[i-1]) nums[mk++]=nums[i];
    }
    int reslow = N;
    for(int i=0, j=0; i<mk; i++){
      while(j<mk && nums[j]-nums[i] <= N-1) j++;
      reslow = Math.min(reslow, N - (j - i)); // Minimize total moves
    }
    return reslow;
  }
}