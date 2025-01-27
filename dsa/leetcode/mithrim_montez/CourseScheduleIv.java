package dsa.leetcode.mithrim_montez;

import java.util.*;

public class CourseScheduleIv {

    /**
     * https://leetcode.com/problems/course-schedule-iv/description/?envType=daily-question&envId=2025-01-27
     *
     * The courses can be represented as nodes of a graph. Build an adjacency list representing
     * the direct dependencies (prerequisites). For each query, perform a BFS to determine if the
     * first course is reachable from the second course. Utilize a boolean array to track visited
     * courses during BFS traversal.
     *
     * TC: O(n*(m+n)) SC: O(n^2)
     * #dfs #bfs #graph #topological-sort #medium
     */

    private boolean isPrerequisite(Map<Integer, List<Integer>> adjList, boolean[] visited, int start, int target) {
        if (start == target) return true;
        if (visited[start]) return false;
        visited[start] = true;
        List<Integer> edges = adjList.get(start);
        if (edges != null) {
            for (int neighbor : edges) {
                if (isPrerequisite(adjList, visited, neighbor, target)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] edge : prerequisites) {
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }
        List<Boolean> result = new ArrayList<>();

        for (int i = 0; i < queries.length; i++) {
            boolean[] visited = new boolean[numCourses];
            result.add(isPrerequisite(adjList, visited, queries[i][0], queries[i][1]));
        }

        return result;
    }
}