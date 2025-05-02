package dsa.leetcode.VallesMarineris;

import java.util.*;

public class CountOfInterestingSubarrays {

  /**
   * https://leetcode.com/problems/count-of-interesting-subarrays/?envType=daily-question&envId=2025-04-25&sorting=W3sic29ydE9yZGVyIjoiREVTQ0VORElORyIsIm9yZGVyQnkiOiJGUk9OVEVORF9JRCJ9XQ%3D%3D
   *
   * The task is to find subarrays where `cnt % modulo == k`.
   * Calculate prefix sums and use a hashmap to track frequencies of
   * prefix sums modulo `modulo`. For each element, check how many times
   * `(prefix - k) % modulo` has been encountered.
   *
   * TC: O(n), SC: O(n)
   * #array #hash-table #prefix-sum #medium
   */

  public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
    int n = nums.size();
    HashMap<Integer, Integer> cnt = new HashMap<>();
    long res = 0;
    int prefix = 0;
    cnt.put(0, 1);
    for (int i = 0; i < n; i++) {
      prefix += nums.get(i) % modulo == k ? 1 : 0;
      res += cnt.getOrDefault((prefix - k + modulo) % modulo, 0);
      cnt.put(prefix % modulo, cnt.getOrDefault(prefix % modulo, 0) + 1);
    }
    return res;
  }
}