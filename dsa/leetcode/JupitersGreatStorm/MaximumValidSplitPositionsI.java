package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-valid-split-positions-i/
 *
 * For a fixed array `arr`, `comp` scores it in O(n log(max)) via prefix/suffix GCDs: `pre[i]` = gcd(arr[0..i]) built left to right; `invgcd`, built right to left starting from the last element, tracks gcd(arr[i+1..m-1]) as it's rolled leftward one element at a time. A split at `i` is valid exactly when `pre[i] == invgcd` at the moment `invgcd` still represents the suffix starting at i+1 (checked before folding arr[i] into it).
 *
 * To let this same array-scoring routine simulate "removing one element" without allocating a shorter array, the removed index is temporarily zeroed (`nums[i] = 0`) instead of deleted. Since all values are >= 1 normally, a 0 is a sentinel for "skip this slot": `comp`'s suffix loop explicitly `continue`s over zeros so they never enter `invgcd`'s rolling gcd or trigger a match, while the prefix array still computes `gcd(pre[i-1], 0) == pre[i-1]` at that slot (a harmless no-op since gcd(x, 0) = x), leaving the real prefix values undisturbed for indices past the hole.
 *
 * Because n <= 1000, trying "no removal" plus "remove index i" for every i (each an O(n log(max)) rescore) is only O(n^2 log(max)), well within limits — no need for the incremental technique the harder version (Maximum Valid Split Positions II) requires.
 *
 * TC: O(n^2 log(max)) SC: O(n)
 * #array #math #gcd #prefix-sum #medium
 */

class MaximumValidSplitPositionsI {
    private int gcd(int x, int y) {
        while (y != 0) {
            int tx = x;
            x = y;
            y = tx % y;
        }
        return x;
    }

    private int comp(int []nums){
        int []pre = new int[nums.length];
        pre[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            pre[i] = gcd(pre[i-1], nums[i]);
        }
        int res = 0, invgcd = nums[nums.length-1];
        for(int i=nums.length-2; i>=0; i--){
            if(nums[i] == 0) continue;
            if(pre[i] == invgcd) res++;
            invgcd = gcd(invgcd, nums[i]);
        }
        return res;
    }
    public int maxValidSplits(int[] nums) {
        if(nums.length == 1) return 0;
        int res = comp(nums);
        if(nums.length >= 3) for(int i=0; i<nums.length; i++){
            int tmp = nums[i];
            nums[i] = 0;
            res = Math.max(res, comp(nums));
            nums[i] = tmp;
        }
        return res;
    }
}
