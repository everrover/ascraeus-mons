package dsa.leetcode.EuropasOceanWorld;

import java.util.Arrays;

public class LongestConsecutiveSequence {
    /**
     * https://leetcode.com/problems/longest-consecutive-sequence/
     * 
     * Sort the array and then iterate while counting consecutive sequences considering duplicates.
     * This is a simple yet effective way, despite the algorithm's constraint.
     * 
     * TC: O(n log n) due to sorting SC: O(1)
     * #array #hash-table #union-find #medium
     */
    
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int start = 0, end = 0, ans = 1, ansTmp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[end] + 1 == nums[i]) {
                end = i;
                ansTmp++;
            } else if (nums[end] == nums[i]) {
                end = i;
            } else {
                ansTmp = 1;
                start = end = i;
            }
            ans = Integer.max(ansTmp, ans);
        }
        return ans;
    }
}