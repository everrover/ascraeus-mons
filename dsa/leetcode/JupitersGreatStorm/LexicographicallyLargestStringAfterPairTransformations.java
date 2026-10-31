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

    /**
     * Approach 2: same greedy decomposition, memoized across queries. Peeling off the largest
     * power 2^k (k <= 25) from c leaves remainder c - 2^k, so answer(c) = letter(k) + answer(rem).
     * The chain of remainders is walked down until a cached value (or 0) is hit, then filled back
     * up bottom-up, so repeated / overlapping counts across nums reuse already-built suffixes.
     *
     * TC: O(n · L) where L <= 26 + MAX/2^25 is the answer length SC: O(MAX) for the memo array
     */
    public String[] largestStringMemo(int[] nums) {
        String[] res = new String[nums.length];
        int mx = 0;
        for(int num: nums) mx = Math.max(mx, num);
        String[] memo = new String[mx+1];
        for (int i = 0; i < nums.length; i++) {
            res[i] = decompose(nums[i], memo);
        }
        return res;
    }

    private String decompose(int c, String[] memo) {
        if (c == 0) return "";
        Deque<int[]> chain = new ArrayDeque<>(); // {cur, letterIndex, remainder}
        int cur = c;
        while (cur > 0 && memo[cur]==null) {
            int chloc = Math.min(25, (int)log2(cur));
            int rem = cur - (1 << chloc);
            chain.push(new int[]{cur, chloc, rem});
            cur = rem;
        }
        // Bottom-up: fill from the smallest remainder up to c, reusing cached values.
        while (!chain.isEmpty()) {
            int[] step = chain.pop();
            String remStr = step[2] == 0 ? "" : memo[step[2]];
            memo[step[0]]=(char) ('a' + step[1]) + remStr;
        }
        return memo[c];
    }
}
