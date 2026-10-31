package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/maximize-sum-after-at-most-k-digit-rearrangements/
 *
 * Greedy on gain, not on the rearranged value. Each element is rearranged independently, and the
 * best rearrangement of x is its digits sorted in descending order, call it max(x). Spending an
 * operation on x therefore adds exactly gain(x) = max(x) - x to the sum, regardless of what happens
 * to any other element.
 * - The operations are interchangeable and the gains are independent, so the best choice of at most
 *   k elements is simply the k largest gains (exchange argument: swapping a chosen smaller gain for
 *   an unchosen larger one can only raise the sum).
 * - Sorting by max(x) alone fails: a large x can have a large max(x) but a small gain. E.g. for
 *   [26,6,37,41,27,29] with k = 2, 37 -> 73 gains 36 while 27 -> 72 gains 45.
 * - gain(x) >= 0 always, and elements with gain 0 consume an operation for nothing, so they are
 *   skipped; "at most k" means the leftover operations can go unused.
 *
 * TC: O(n log n) SC: O(n)
 * #array #math #greedy #sorting #medium
 */

class MaximizeSumAfterAtMostKDigitRearrangements {
  private long format(int num){
    List<Integer> digs = new ArrayList<>(10);
    int tmp = num, multiplier = 1;
    long res = 0;
    while(tmp > 0){
      digs.add(tmp%10);
      tmp /= 10;
    }
    Collections.sort(digs);
    for(int dig: digs) {
      res += multiplier*dig;
      multiplier *= 10;
    }
    return res;
  }
  public long maximizeSum(int[] nums, int k) {
    long res = 0;
    int [][]numsnew = new int[nums.length][2];
    long [][]numsmax = new long[nums.length][2];
    for(int i=0; i<nums.length; i++){
      numsmax[i][0] = format(nums[i])-nums[i]; numsmax[i][1] = i;
      res += nums[i];
    }
    Arrays.sort(numsmax, (a,b)->(int)(b[0]-a[0]));
    for(int i=0; i<nums.length && k > 0; i++){
      int comp = (int)numsmax[i][1];
      if(numsmax[i][0] != nums[comp] && numsmax[i][0] > 0){
        res = res + numsmax[i][0];
        k--;
      }
      if(k == 0) break;
    }
    return res;
  }
}
