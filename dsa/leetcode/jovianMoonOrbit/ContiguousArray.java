package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class ContiguousArray {

  /**
   * https://leetcode.com/problems/contiguous-array/
   *
   * The solution uses a hashmap to store the first occurrence of the difference between the count of zeros and ones (represented as o and z).
   * By tracking this difference, when the same difference is found again, it means the subarray between these indices has equal number of zeros and ones. The maximum length is updated accordingly.
   *
   * TC: O(n) SC: O(n)
   * #array #hash-table #prefix-sum #medium
   */

  public int findMaxLength(int[] nums) {
    int res = 0, o = 0, z = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    for(int i=0; i<nums.length; i++){
      if(nums[i] == 0) z++;
      else o++;
      if(map.containsKey(z-o)) res = Math.max(res, i-map.get(z-o));
      map.putIfAbsent(z-o, i);
    }
    return res;
  }
}