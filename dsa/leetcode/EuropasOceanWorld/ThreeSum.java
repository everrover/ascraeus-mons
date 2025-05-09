package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class Solution {

    /**
     * https://leetcode.com/problems/3sum/description/
     *
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k,
     * and j != k, and nums[i] + nums[j] + nums[k] == 0.
     * The solution set must not contain duplicate triplets.
     *
     * To solve this, sort the array and use a two-pointers technique to find pairs that sum up with a fixed element
     * to zero, skipping over duplicates as necessary.
     *
     * TC: O(n^2) SC: O(1) - excluding input and output space
     * #array #two-pointers #sorting #medium
     */

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < (nums.length - 2); i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;  // skip duplicate elements
            }
            int reqdSum = -nums[i];
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum == reqdSum) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // Skip duplicates for the l and r pointers
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum < reqdSum) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }
}