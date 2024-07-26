package dsa.leetcode.kuiperBelt;

public class CountSubarraysWithScoreLessThanK {
 
  /**
   * https://leetcode.com/problems/count-subarrays-with-score-less-than-k/submissions/
   *
   * To count the number of subarrays whose score is strictly less than `k`, we can use a sliding window approach. Maintain a running sum (`prefix`) of the current subarray. Expand the window by moving the right pointer (`r`) and keep shrinking it from the left (`l`) until the score of the subarray is less than `k`.
   * 
   * TC: O(n) SC: O(1)
   * #array #binary-search #sliding-window #prefix-sum #hard
   */
  
  public long countSubarrays(int[] nums, long k) {
    long res = 0;
    int l=0, r=0;
    long prefix = 0L;
    while(r < nums.length) {
      prefix += nums[r];
      while(prefix * (r - l + 1) >= k) {
        prefix -= nums[l];
        l++;
      }
      res += r - l + 1;
      r++;
    }
    return res;
  }
}