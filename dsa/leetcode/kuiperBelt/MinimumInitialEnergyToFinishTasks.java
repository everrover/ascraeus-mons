package dsa.leetcode.KuiperBelt;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/
 * This solution uses sorting and a greedy approach to determine the minimum initial energy required.
 * 
 * TC: O(n log n) due to sorting the tasks array
 * SC: O(1)
 * #array #greedy #sorting #hard
 */
public class MinimumInitialEnergyToFinishTasks {
    public int minimumEffort(int[][] tasks) {
        int res = 0, diff = 0;
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0])); // Sort tasks based on the difference between minimum and actual energy
        for (int i = 0; i < tasks.length; i++) {
            // If the current task's minimum energy is greater than the remaining diff
            if (tasks[i][1] > diff) {
                res += tasks[i][1] - diff; // Add the difference to the result
                diff = tasks[i][1]; // Update the diff
            }
            diff -= tasks[i][0]; // Subtract the actual energy spent
        }
        return res; // Return the minimum initial energy required
    }
}