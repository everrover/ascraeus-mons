package dsa.leetcode.mithrim_montez;

import java.util.Arrays;

public class MinimumCostToMakeArraysIdentical {

    /**
     * https://leetcode.com/problems/minimum-cost-to-make-arrays-identical/description/
     *
     * To minimize the cost, first align the arrays by sorting both. Then, calculate the cost for each entry's adjustment.
     * The splitting and rearranging cost is fixed and needs to be added if any operation is performed.
     *
     * TC: O(n log n) SC: O(1)
     * #sorting #greedy #array #medium
     */

    public long minCost(int[] arr, int[] brr, long k) {
        Arrays.sort(arr);
        Arrays.sort(brr);
        long tmpres = k;
        long res = 0L;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // Calculate the cost for each element adjustment
            res += (long) Math.abs(arr[i] - brr[i]);
        }
        return tmpres + res;
    }
}