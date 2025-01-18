package dsa.leetcode.mithrim_montez;

import java.util.Arrays;

public class MinimumCostToMakeArraysIdentical {

    /**
     * https://leetcode.com/problems/minimum-cost-to-make-arrays-identical/description/
     *
     * To minimize the cost, either we split the array as per the provided condition(additional cost k), or we dont split.
     * In a single split, we can re-arrange the elements of array in any order.
     * 
     * To minimize the cost, for each element in B, we need to find an element in A which has min abs difference.
     * On attempting with a few examples found that smallest element in A should be paired with smallest element in B and so on.
     * If any element in B is paired with any element at another index the sum of abs differences increases...
     * 
     * Can do minima calc math here, but the above observation is enough to solve the problem.
     * 
     * Tried on this: with k=3, k=40, k=65
     * 4 7 8 -1 -3 2 -9
     * 5 2 1  1 -8 -7 -20
     *
     * TC: O(n log n) SC: O(1)
     * #sorting #greedy #array #medium
     */

    public long minCost(int[] arr, int[] brr, long k) {
        int n = arr.length;
        long res = 0L;
        // we dont split
        for(int i=0; i<n; i++){
            res += (long)Math.abs(arr[i] - brr[i]);
        }
        long tmpres = k;
        Arrays.sort(arr);
        Arrays.sort(brr);
        for(int i=0; i<n; i++){
            tmpres += (long)Math.abs(arr[i]-brr[i]);
        }
        return Math.min(res, tmpres);
    }
}