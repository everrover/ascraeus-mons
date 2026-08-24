package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-subarray-with-at-most-k-distinct-prime-factors/
 *
 * A sieve precomputes, for every value up to 1e5, its list of distinct prime factors: for each prime i, every multiple j of i gets i appended to valtoprime[j] (standard "smallest/all prime factors via sieve" trick, run once and cached statically across calls).
 *
 * With per-value prime-factor lists precomputed, the problem reduces to the classic "longest subarray with at most K distinct X" sliding window. A HashMap primescounter tracks, for each prime factor currently in the window, how many window elements contribute it. The right pointer extends the window while the number of distinct keys in primescounter stays <= k, incrementing the count for each prime factor of the entering element. As l advances, the prime factors of the leaving element are decremented and removed once their count hits zero, keeping primescounter.size() exactly equal to the window's distinct prime factor count. res tracks the widest window seen whose distinct-prime-factor count was within budget.
 *
 * TC: O(n log log MAXV) SC: O(MAXV)
 * #array #hash-table #sliding-window #sieve #number-theory #medium
 */

class LongestSubarrayWithAtMostKDistinctPrimeFactors {
    private static List<Integer> primes = null;
    private static Map<Integer, List<Integer>> valtoprime = null;

    private List<Integer> sievegen(){
        final int sz = (int)1e5 ;
        primes = new ArrayList<>(sz);
        valtoprime = new HashMap<>();
        final int pmax = sz;
        // final int pmax = (int)Math.ceil(Math.sqrt(sz));
        for(int i=2; i<=pmax; i++){
            if(valtoprime.containsKey(i)) continue;
            primes.add(i);
            for(int j=i; j<=sz; j+=i) {
                valtoprime.putIfAbsent(j, new ArrayList<>());
                valtoprime.get(j).add(i);
            }
        }
        return primes;
    }
    public int longestSubarray(int[] nums, int k) {
        if(primes == null) sievegen();
        Map<Integer, Integer> primescounter = new HashMap<>();
        int res = 0;
        for(int l=0, r=0; l<nums.length; l++){
            while(r<nums.length && primescounter.size() <= k){
                res = Math.max(res, r-l);
                for(int primenum: valtoprime.get(nums[r])){
                    primescounter.putIfAbsent(primenum, 0);
                    primescounter.put(primenum, primescounter.get(primenum)+1);
                }
                r++;
            }
            if(r == nums.length && primescounter.size() <= k) res = Math.max(res, r-l);
            for(int primenum: valtoprime.get(nums[l])){
                if(primescounter.get(primenum) <= 1){
                    primescounter.remove(primenum);
                }else{
                    primescounter.put(primenum, primescounter.get(primenum)-1);
                }
            }
        }
        return res;
    }
}
