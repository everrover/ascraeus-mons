package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array-ii/
 *
 * Sort nums, then sweep it while maintaining `cl`, the smallest value not yet confirmed present — every integer in [lower, cl) is guaranteed covered by nums so far. For each sorted num: values below `cl` are duplicates or already accounted for (skip them), and once num exceeds upper the rest of the range can't affect anything left inside [lower, upper] (stop early). Otherwise, if num == cl the frontier just advances by one (no gap yet); if num > cl, everything in [cl, num-1] is missing, so that range is emitted and the frontier jumps to num+1.
 *
 * This single pass naturally handles every edge case in one uniform rule instead of separate checks: nothing in nums touching [lower, upper] leaves `cl == lower` untouched through the whole loop, and the trailing `if(cl <= upper)` after the loop catches both "loop never ran/never advanced cl" and "nums exhausted before reaching upper" — a range from the last confirmed point to upper.
 *
 * TC: O(n log n) SC: O(n)
 * #array #sorting #two-pointers #medium
 */

class FindAllNumbersDisappearedInAnArrayII {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new LinkedList<>();
        int cl = lower;
        Arrays.sort(nums);
        for(int num: nums){
            if(num < cl) continue;
            else if(num > upper) break;

            if(cl == num) cl++;
            else {
                res.add(List.of(cl, num-1));
                cl = num+1;
            }
        }
        if(cl <= upper) res.add(List.of(cl, upper));
        return res;
    }
}
