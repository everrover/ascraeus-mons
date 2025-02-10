package dsa.leetcode.VallesMarineris;

import java.util.*;

public class MaximumEmployeesToBeInvitedToAMeeting {

    /**
     * https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/description/
     *
     * Construct a graph from favorite list, treat each favorite relationship as a directed edge.
     * Detect the cycles in this directed graph or deal with longest path if cycles are of length 2.
     * Count maximum employees that can be seated by either selecting cycles directly or combining chains.
     * 
     * TC: O(n) SC: O(n)
     * #graph #dfs #cycle #hard
     */

    private int dfs(int idx, List<Set<Integer>> favRev, boolean[] isVisited) {
        if (isVisited[idx]) {
            return 0;
        }
        int res = 0;
        isVisited[idx] = true;
        for (int i : favRev.get(idx)) {
            res = Math.max(res, 1 + dfs(i, favRev, isVisited));
        }
        return res;
    }

    public int maximumInvitations(int[] favs) {
        // Implementation of solution logic continues...
    }
}