package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/finish-time-of-tasks-i/
 *
 * Postorder DFS on the task tree. Leaf nodes return baseTime[i] directly. For each non-leaf, recurse into all children to collect their finish times, then track only the min (earliest) and max (latest). The node's own duration is (latest - earliest) + baseTime[i] — the spread between child finish times determines how long the parent must wait before it can finish — and its finish time is latest + ownDuration.
 *
 * TC: O(n) SC: O(n)
 * #tree #dfs #medium
 */

class FinishTimeOfTasksI {
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        int[][] torqavemi = edges.clone();
        
        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        
        for (int[] edge : torqavemi) {
            int parent = edge[0];
            int child = edge[1];
            children.get(parent).add(child);
        }
        
        return dfs(0, children, baseTime);
    }
    
    private long dfs(int node, List<List<Integer>> children, int[] baseTime) {
        List<Integer> childList = children.get(node);
        
        if (childList.isEmpty()) {
            return baseTime[node];
        }
        
        long earliest = Long.MAX_VALUE;
        long latest = Long.MIN_VALUE;
        
        for (int child : childList) {
            long childFinish = dfs(child, children, baseTime);
            earliest = Math.min(earliest, childFinish);
            latest = Math.max(latest, childFinish);
        }
        
        long ownDuration = (latest - earliest) + baseTime[node];
        return latest + ownDuration;
    }
}
