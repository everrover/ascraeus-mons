package dsa.leetcode.VallesMarineris;

import java.util.*;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
    
    /**
     * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/?envType=weekly-question&envId=2025-04-08
     *
     * To find the number of connected components in the graph, iterate over each node, and for each unvisited node, perform a DFS marking all reachable nodes as visited. Each DFS represents one connected component.
     * 
     * TC: O(n + m) SC: O(n + m) where n is the number of nodes, and m is the number of edges.
     * #depth-first-search #breadth-first-search #union-find #graph #medium
     */

    public int countComponents(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(i, visited, adjList);
            }
        }
        return count;
    }
    
    private void dfs(int node, boolean[] visited, List<List<Integer>> adjList) {
        visited[node] = true;
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, adjList);
            }
        }
    }
}