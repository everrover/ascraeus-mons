package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-resilient-subarray-i/
 *
 * Normalize every element to its remainder mod k. A window is resilient iff every element in it shares the same remainder r: since the window sum S must satisfy S-x ≡ 0 (mod k) for every x in it, all x must be congruent to S mod k, and since S is itself the sum of such x's, all elements must be mutually congruent mod k. So the problem reduces to finding the longest run of equal remainders [i..j] for which the run's sum is additionally divisible by k after removing its last element (equivalently, by symmetry, after removing any single element, since all elements share the same residue). The single pass extends a run while nums[i] == nums[i-1] (same residue), tracking the running sum crem of the current run; whenever (crem - nums[i]) % k == 0 the whole run is resilient and the running max is updated.
 *
 * TC: O(n) SC: O(1)
 * #array #sliding-window #math #medium
 */

class LongestResilientSubarrayI {
  public int resilientSubarray(int[] nums, int k) {
    int i=0, j=0, crem=0, clen=1, res = 1;
    for(i=0; i<nums.length; i++) nums[i] = nums[i]%k; // convert to modulo array
    for(i=1, crem=nums[0]; i<nums.length; i++){
      if(nums[i] == nums[i-1]){
        clen++;
        crem += nums[i];
        if((crem-nums[i])%k == 0) res = Math.max(res, clen);
      }else {
        clen=1;
        crem = nums[i];
      }
    }
    return res;
  }
}
