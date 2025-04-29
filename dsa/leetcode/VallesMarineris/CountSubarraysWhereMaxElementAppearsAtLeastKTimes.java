package dsa.leetcode.VallesMarineris;

import java.util.*;

public class CountSubarraysWhereMaxElementAppearsAtLeastKTimes {

  /**
   * https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/description/?envType=daily-question&envId=2025-04-29
   * 
   * To count subarrays, identify subarrays where the maximum element appears at least k times.
   * Utilize a sliding window approach, counting occurrences of the max element and extending the window as needed.
   * 
   * TC: O(n) SC: O(1)
   * #array #sliding-window #medium
   */
  
  public long countSubarrays(int[] nums, int k) {
    long res = 0;
    int maxEle = Arrays.stream(nums).max().orElseThrow();
    int cnt = 0;
    for (int i = 0, j = 0; i < nums.length; i++) {
      while (j < nums.length && cnt < k) {
        if (nums[j] == maxEle) cnt++;
        j++;
      }
      if (cnt == k) res += nums.length - j + 1;
      if (nums[i] == maxEle) cnt--;
    }
    return res;
  }
}