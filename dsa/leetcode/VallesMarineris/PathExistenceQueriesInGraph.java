package dsa.leetcode.VallesMarineris;

import java.util.*;

public class PathExistenceQueriesInGraph {
  
  /**
   * https://leetcode.com/problems/path-existence-queries-in-a-graph-i/description/
   *
   * Utilize Union-Find to manage connected components by checking if nodes can be connected based on `maxDiff`.
   * First, preprocess nodes into connected components, then handle queries efficiently with the find operation.
   * 
   * TC: O(n + q) SC: O(n)
   * #graph #union-find #array #medium
   */
  
  public boolean[] pathQueries(int n, int[] nums, int maxDiff, int[][] queries) {
    UnionFind uf = new UnionFind(n);
    // Since nums[] is sorted, adjacent nodes may be connectable
    for (int i = 1; i < n; i++) {
      if (nums[i] - nums[i - 1] <= maxDiff) {
        uf.union(i, i - 1);
      }
    }
    boolean[] result = new boolean[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int u = queries[i][0];
      int v = queries[i][1];
      result[i] = uf.find(u) == uf.find(v);
    }
    return result;
  }

  // Standard Union-Find with path compression
  static class UnionFind {
    int[] parent;
    UnionFind(int size) {
      parent = new int[size];
      for (int i = 0; i < size; i++) {
        parent[i] = i;
      }
    }
    int find(int x) {
      if (parent[x] != x) {
        parent[x] = find(parent[x]); // Path compression
      }
      return parent[x];
    }
    void union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX != rootY) {
        parent[rootY] = rootX;
      }
    }
  }
}