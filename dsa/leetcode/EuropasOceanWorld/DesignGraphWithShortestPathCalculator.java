package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

/**
 * https://leetcode.com/problems/design-graph-with-shortest-path-calculator/description/?envType=problem-list-v2&envId=design
 *
 * Used Dijkstra’s algorithm to calculate the shortest paths and associated queries.
 * After adding each edge, update the graph.
 * 
 * TC: O((n + e) log n), SC: O(n + e) where n is number of nodes, e is number of edges.
 * #graph #design #priorityqueue #shortestpath #hard
 */

class Graph {
    private List<int[]>[] adj;
    private int n;

    public Graph(int n, int[][] edges) {
        this.n = n;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) adj[edge[0]].add(new int[]{edge[1], edge[2]});
    }

    public void addEdge(int[] edge) {
        adj[edge[0]].add(new int[]{edge[1], edge[2]});
    }

    public int shortestPath(int node1, int node2) {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{node1, 0});
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[node1] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0], d = current[1];
            if (node == node2) return d;

            for (int[] neighbor : adj[node]) {
                int nextNode = neighbor[0], cost = neighbor[1];
                if (dist[nextNode] > d + cost) {
                    dist[nextNode] = d + cost;
                    pq.offer(new int[]{nextNode, dist[nextNode]});
                }
            }
        }

        return -1;
    }
}