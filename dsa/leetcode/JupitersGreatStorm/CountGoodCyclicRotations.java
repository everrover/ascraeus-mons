package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/count-good-cyclic-rotations/
 *
 * Each rotation splits the circular array into two contiguous halves of length n/2; a rotation is good iff sum(first half) > sum(second half). Rather than recomputing both sums from scratch for each of the n rotations (O(n) each, O(n^2) total), maintain them incrementally as a circular sliding window.
 *
 * Start with `first` = sum of indices [0, n/2) and `last` = sum of the rest, check rotation 0. Advancing the window by one rotation shifts both halves by one position: the element leaving the first half is `nums[i]` and the element entering it is `nums[(i+n2)%n]` (the element that just left the last half, wrapped around); update both running sums with a single subtract/add pair per step. Since each rotation only changes each half's boundary by one element, this reduces the whole computation to O(n) total.
 *
 * Sums are kept as `long` because n can be up to 1e5 and values up to 1e9, so a half-sum can reach ~1e14, overflowing `int`.
 *
 * TC: O(n) SC: O(1)
 * #array #sliding-window #prefix-sum #medium
 */

class CountGoodCyclicRotations {
    public int countGoodRotations(int[] nums) {
        int n = nums.length, n2 = nums.length/2;
        int res = 0;
        long first = 0, last = 0;
        for(int i=0; i<n2; i++){
            first += nums[i];
        }
        for(int i=n2; i<n; i++){
            last += nums[i];
        }
        if(first > last) res++;
        for(int i=0; i<n-1; i++){
            first = first - nums[i] + nums[(i+n2)%n];
            last = last + nums[i] - nums[(i+n2)%n];
            if(first > last) res++;
        }
        return res;
    }
}
