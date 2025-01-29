package dsa.leetcode.mithrim_montez;

class Solution {
  /**
   * https://leetcode.com/problems/redundant-connection/description/?envType=daily-question&envId=2025-01-29
   *
   * Use the Disjoint Set (Union-Find) data structure to keep track of connected components.
   * Traverse the edge list and check if any edge connects two nodes that are already connected;
   * if so, that's the redundant edge.
   *
   * TC: O(n * α(n)) SC: O(n)
   * #depth-first-search #breadth-first-search #union-find #graph #medium
   */
  private static class DisjointSet {
    int []parent;
    int []rank;
    DisjointSet(int n) {
      parent = new int[n];
      rank = new int[n];
      for(int i=0; i<n; i++) {
        parent[i] = i;  // Initialize each node to be its own parent
      }
    }
    public int find(int x) {
      if(parent[x] != x) {
        parent[x] = find(parent[x]);  // Path compression
      }
      return parent[x];
    }
    public void union(int x, int y) {
      int px = find(x);
      int py = find(y);
      if(px == py) return;  // Nodes are already connected
      if(rank[px] > rank[py]) {
        parent[py] = px;
      } else {
        parent[px] = py;
        rank[py]++;  // Update rank when necessary
      }
    }
  }
}