package dsa.leetcode.EuropasOceanWorld;

/**
 * https://leetcode.com/problems/container-with-most-water/description/
 *
 * Use two pointers, one at the beginning and one at the end of the array, moving towards each other.
 * Calculate the area at each step, keeping track of the maximum found.
 * Move the pointer pointing to the shorter line inwards.
 * 
 * INTUTION - Against a given larger line at the extremes, the area is determined by the shorter line.
 * Since, IF best area with extremes(larger width) is already calculated, we need to move the pointer
 * pointing to the shorter line inwards, as moving the it will always decrease the area less than
 * moving the pointer pointing to the larger line.
 * 
 * x y ... a b
 * A = min(x, b) * width, B = min(x, a) * width-1, C = min(x, b) * width-1, D = min(y, a) * width-2
 * try all possible combinations of x, y, a, b and it proves the above intuition
 *
 * TC: O(n), SC: O(1)
 * #array #two-pointers #greedy #medium
 */

class ContainerWithMostWater {
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