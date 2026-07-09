package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/palindromic-subarray-sum/description/
 *
 * Applies Manacher's algorithm to find the maximum-sum palindromic subarray in O(n). The array is transformed into a length-(2n+1) interleaved sequence by inserting a sentinel value (1e6) between every element and at even positions — this unified representation handles both odd- and even-length palindromes with a single pass. Manacher's classic center/right-boundary reuse gives each position's palindrome radius p[i] in amortized O(1). Since all values are positive, the best palindrome at each center is the longest one; its sum is read off a prefix-sum array in O(1) via the relation l=(i-p[i])/2, r=(i+p[i])/2.
 *
 * TC: O(n) SC: O(n)
 * #array #string #manacher #prefix-sum #hard
 */

class PalindromicSubarraySum {
    private final int NUM = (int)1e6;
    public long getSum(int[] nums) {
        final int n = nums.length;
        long res = 0;
        long []prefix = new long[n+1];
        for(int i=0; i<n; i++) prefix[i+1] = prefix[i]+nums[i];
        int []p = getPalindromicRange(nums);
        for(int i=0; i<2*n+1; i++){
            int l = (i-p[i])/2, r = (i+p[i])/2;
            res = Math.max(prefix[r] - prefix[l], res);
        }
        return res;
    }

    private int[] getPalindromicRange(int []nums){
        int n = nums.length;
        int []seq = new int[2*n+1];
        Arrays.fill(seq, NUM);
        for(int i=0; i<n; i++) seq[2*i+1] = nums[i];
        int m = seq.length;
        int []p = new int[m];
        int c=0, r=0;
        for(int i=1; i<m; i++){
            if(i<r) p[i] = Math.min(r-i, p[2*c-i]);
            while(i-p[i]-1 >= 0 && i+p[i]+1<m && seq[i-p[i]-1] == seq[i+p[i]+1]){
                p[i]++;
            }
            if(i+p[i] > r) {
                c = i; r = i+p[i];
            }
        }
        return p;
    }
}
