package dsa.leetcode.VallesMarineris;

import java.util.PriorityQueue;

public class MinimumOperationsToExceedThresholdValueII {

    /**
     * https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/description/?envType=daily-question&envId=2025-02-13
     *
     * Use a priority queue to always extract the two smallest values from the list. Combine them according to the operation
     * and place the result back in the queue if it doesn't satisfy the threshold. Continue until all values satisfy the threshold.
     * 
     * TC: O(n log n) SC: O(n)
     * #array #heap #priority-queue #simulation #medium
     */

    public int minimumOperations(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }
        int operations = 0;
        while (!pq.isEmpty()) {
            if (pq.size() < 2) break; // Cannot perform operation with less than 2 elements
            int x = pq.poll();
            int y = pq.poll();
            long newValue = 2L * x + y;
            if (newValue < k) {
                pq.add((int) newValue);
            }
            operations++;
        }
        return operations;
    }
}