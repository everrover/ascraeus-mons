package dsa.leetcode.KuiperBelt;

public class CountSubarraysWithScoreLessThanK {
 
  /**
   * https://leetcode.com/problems/count-subarrays-with-score-less-than-k/submissions/
   *
   * Needed the hint here. What if we add a new element in stream? We need to go from right->left to find index 'j'
   * such that sum(nums[i:j])*(j-i+1)<k. Since such a sum is always increasing, we can use binary search to find 'j'.
   *
   * binary search approach : For each index 'i' we find the leftmost index 'j' such that (prefix[i]-prefix[j-1])*(j-i+1)<k.
   * If we find such a 'j', then the next num will always be >'j' and <='i'. So, we need a sliding window.
   *
   * Hence, a sliding window approach. Maintain a running sum (`prefix`) of the current subarray. Expand the window by
   * moving the right pointer (`r`) and keep shrinking it from the left (`l`) until the score of the subarray is less
   * than `k`.
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

  /*

  // binary search approach
  public long countSubarrays(int[] nums, long k) {
    long res = 0;
    long []prefix = new long[nums.length];
    prefix[0] = nums[0];
    for(int i=1; i<nums.length; i++) prefix[i] = prefix[i-1] + nums[i];
    for(int i=0; i<nums.length; i++){
      int l = 0, r = i;
      while(l<r){
        int m = l + (r-l)/2;
        if((prefix[i] - (m>0?prefix[m-1]:0))*(i-m+1) < k) r = m;
        else l = m+1;
      }
      res += i-l+1;
    }
    return res;
  }
   */
}