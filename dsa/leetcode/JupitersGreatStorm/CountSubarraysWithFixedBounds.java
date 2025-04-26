package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

public class CountSubarraysWithFixedBounds {

  /**
   * https://leetcode.com/problems/count-subarrays-with-fixed-bounds/submissions/1618276109/?envType=daily-question&envId=2025-04-26
   *
   * Iterate through the array, maintaining two queues to track indices of minK and maxK.
   * When both queues are non-empty, count subarrays formed from maximum of those indices to current.
   * Divide the array based on boundaries formed by elements out of range.
   *
   * TC: O(n), where n is the length of nums
   * SC: O(n) in the worst-case due to queue operations
   * #array #queue #sliding-window #monotonic-queue #hard
   */

  public long countSubarrays(int[] nums, int minK, int maxK) {
    Queue<Integer> maxJ = new LinkedList<>(), minJ = new LinkedList<>();
    long res = 0L;
    for (int i = 0, j = 0; i < nums.length; i++) {
      j = Math.max(i, j);
      while (j < nums.length && nums[j] <= maxK && nums[j] >= minK) {
        if (nums[j] == maxK) { maxJ.offer(j); }
        if (nums[j] == minK) { minJ.offer(j); }
        j++;
      }
      if (!maxJ.isEmpty() && !minJ.isEmpty()){
        res += (j - Math.max(maxJ.peek(), minJ.peek()));
      }
      while (!maxJ.isEmpty() && maxJ.peek() < i) maxJ.poll();
      while (!minJ.isEmpty() && minJ.peek() < i) minJ.poll();
    }
    return res;
  }
}