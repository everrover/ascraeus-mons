package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

public class EarliestSecondToMarkIndicesI {
  /**
   * https://leetcode.com/problems/earliest-second-to-mark-indices-i/
   * The method attempts to find the earliest second when all indices can be marked.
   * It uses binary search to minimize the search space, checking if it's possible to mark
   * all indices by a given second.
   * 
   * How check is implemented:
   * Mark the last index where mark can be placed.
   * If we reach at last marked, we subtract already decremented elements and marked ones.
   * i.e. a mark is valid if (it's position) - (already marked) - (already decremented) = 
   * ((number of decrements we can perform)) > (value at that position.)
   * 
   * Thought of greedy approach but it was good for validation(with same steps as above), 
   * but needed brute-force over each combination of marks to find the earliest second.
   *
   * TC: O(n log m) SC: O(n)
   * #binary-search #array #optimization #medium
   */
  public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
    int l = 0, r = changeIndices.length-1, mid;
    int res = -1;
    while(l<r){
      mid = (l+r)/2;
      if(check(nums, changeIndices, mid)){
        r = mid;
      }else{
        l = mid+1;
      }
    }
    return check(nums, changeIndices, l)?(l+1):-1;
  }

  private boolean check(int[] nums, int[] changeIndices, int s) {
    int[] last = new int[nums.length];
    Arrays.fill(last, -1);
    for (int i = 0; i <= s; i++) {
      last[changeIndices[i]-1] = i;
    }
    int marked = 0, operations = 0;
    for (int i = 0; i <= s; i++) {
      if (i == last[changeIndices[i]-1]) {
        if (i-marked-operations < nums[changeIndices[i]-1]) return false;
        operations += nums[changeIndices[i]-1];
        marked++;
      }
    }
    return marked == nums.length;
  }
}