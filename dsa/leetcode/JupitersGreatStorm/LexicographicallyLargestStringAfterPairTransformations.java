package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/lexicographically-largest-string-after-pair-transformations/
 *
 * Every 2^k adjacent 'a's can be collapsed into one occurrence of the k-th letter after 'a' (since "aa"->"b" halves count, repeatedly). To make the result lexicographically largest, greedily peel off the biggest power of two <= the remaining count at each step and emit that letter — a larger leading letter always beats any suffix, and emitting the largest available letter first, then recursing on what's left, mirrors a canonical "binary representation" decomposition of x. The letter index is capped at 25 ('z') since "zz" can't be merged further, so counts requiring more than 2^25 combine into multiple 'z's instead of a nonexistent letter beyond 'z'.
 *
 * TC: O(n log MAX) SC: O(1) extra
 * #array #math #greedy #bit-manipulation #medium
 */

class LexicographicallyLargestStringAfterPairTransformations {
    public static double log2(double x) {
        return Math.log(x) / Math.log(2); // Change of base formula
    }
    public String[] largestString(int[] nums) {
        int mx = 0;
        for(int num: nums) mx = Math.max(mx, num);
        String []res = new String[nums.length];
        for(int i=0; i<nums.length; i++) {
            int c = nums[i];
            res[i] = "";
            while(c > 0){
                int chloc = Math.min(25,(int)Math.floor(log2(c)));
                res[i] += (char)('a'+chloc);
                c -= Math.pow(2, chloc);
            }
        }
        return res;
    }
}
