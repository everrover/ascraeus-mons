package dsa.leetcode.VallesMarineris;

import java.util.*;

public class ClosestEqualElementQueries {

    /**
     * https://leetcode.com/problems/closest-equal-element-queries/description/
     * 
     * Use a dictionary that maps each unique value in the array to a sorted list of its indices. 
     * For each query, use binary search on the sorted indices list to find the nearest occurrences 
     * of the target value.
     * 
     * TC: O(n + q * log(n)) SC: O(n)
     * #array #hash-table #binary-search #medium
     */

    public List<Integer> closestEqualElementQueries(int[] nums, int[] queries) {
        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
            valueToIndices.get(nums[i]).add(i);
        }

        List<Integer> res = new ArrayList<>();
        for (int q : queries) {
            int n = nums[q];
            List<Integer> indicesList = valueToIndices.getOrDefault(n, Collections.emptyList());
            if (indicesList.isEmpty() || indicesList.size() == 1 && indicesList.get(0) == q) {
                res.add(-1);
            } else {
                // Binary search for the closest index
                int pos = Collections.binarySearch(indicesList, q);
                if (pos < 0) pos = -pos - 1;
                int distLeft = pos > 0 ? q - indicesList.get(pos - 1) : Integer.MAX_VALUE;
                int distRight = pos < indicesList.size() ? indicesList.get(pos) - q : Integer.MAX_VALUE;
                res.add(Math.min(distLeft, distRight));
            }
        }

        return res;
    }

}