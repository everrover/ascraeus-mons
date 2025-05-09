package dsa.leetcode.EuropasOceanWorld;

/**
 * https://leetcode.com/problems/container-with-most-water/description/
 *
 * Use two pointers, one at the beginning and one at the end of the array, moving towards each other.
 * Calculate the area at each step, keeping track of the maximum found.
 * Move the pointer pointing to the shorter line inwards.
 *
 * TC: O(n), SC: O(1)
 * #array #two-pointers #greedy #medium
 */

class Solution {
    public int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE; // Initialize max area to minimum integer value
        int l = 0, r = height.length - 1; // Two pointers at start and end
        while (l < r) { // Loop until the two pointers meet
            // Calculate current area and update max area
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            // Move the pointer pointing to the shorter line
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return maxArea; // Return the maximum area found
    }
}