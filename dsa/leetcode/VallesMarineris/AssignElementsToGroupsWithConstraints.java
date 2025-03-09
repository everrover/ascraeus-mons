package dsa.leetcode.VallesMarineris;

import java.util.*;

/**
 * https://leetcode.com/problems/assign-elements-to-groups-with-constraints/description/?envType=daily-question&envId=2025-02-14
 *
 * Process each element by finding all the groups divisible by it.
 * Assign the smallest index for elements that satisfy the condition.
 * If no element satisfies, assign -1.
 *
 * TC: O(n + m * log(m)) SC: O(m + max group value)
 * #array #hash-table #medium
 */

class Solution {
    private static final int M = (int)1e9;

    public int[] assignElements(int[] groups, int[] elements) {
        int max = 0;
        int n = groups.length;
        for (int i = 0; i < n; i++)
            max = Math.max(max, groups[i]);
        
        int m = elements.length;
        Set<Integer> values = new HashSet<>();
        for (int i = 0; i < m; i++)
            if (!values.add(elements[i]))
                elements[i] = 0; // mark duplicate elements
                
        int[] valToIdx = new int[max + 1];
        Arrays.fill(valToIdx, -1);

        // For all possible elements, find all possible multiples, add index to them
        for (int i = m - 1; i >= 0; i--) {
            int x = elements[i];
            if (x != 0) 
                for (int y = x; y <= max; y += x) 
                    valToIdx[y] = i;
        }

        int[] assigned = new int[n];
        for (int i = 0; i < n; i++) 
            assigned[i] = valToIdx[groups[i]];
        return assigned;
    }
}