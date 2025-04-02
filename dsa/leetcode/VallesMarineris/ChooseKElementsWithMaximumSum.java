package dsa.leetcode.VallesMarineris;

import java.util.*;

public class ChooseKElementsWithMaximumSum {

    /**
     * https://leetcode.com/problems/choose-k-elements-with-maximum-sum/
     *
     * Sort nums1 and its corresponding nums2 values together based on nums1.
     * Use a max heap to track the top k values of nums2
     * as you process each element in the sorted order.
     *
     * TC: Approximately O(n log n) SC: O(n)
     * #array #sorting #heap #medium
     */

    public int[] chooseKElementsWithMaxSum(int[] nums1, int[] nums2, int k) {
        // Create result array
        int n = nums1.length;
        int[] res = new int[n];
        int[][] nnums = new int[n][3];
        for(int i = 0; i < n; i++) {
            nnums[i] = new int[]{nums1[i], nums2[i], i};
        }
        // Sort based on nums1
        Arrays.sort(nnums, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long rsum = 0;
        int i = 0;
        while(i < n) {
            long rsumprev = rsum;
            int j = i;
            while(j < n && nnums[i][0] == nnums[j][0]) {
                res[nnums[j][2]] = (int)rsumprev;
                rsum += nnums[j][1];
                pq.offer(nnums[j][1]);
                j++;
            }
            while(pq.size() > k) {
                rsum -= pq.poll();
            }
            i = j;
        }
        return res;
    }
}