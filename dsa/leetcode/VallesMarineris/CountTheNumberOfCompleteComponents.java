package dsa.leetcode.VallesMarineris;

import java.util.*;

public class CountTheNumberOfCompleteComponents {
  
  /**
   * https://leetcode.com/problems/count-the-number-of-complete-components/description/?envType=daily-question&envId=2025-03-27
   *
   * Find the connected components of an undirected graph using depth-first search (DFS) or breadth-first search (BFS).
   * For each connected component, count the number of nodes and edges in the component.
   * A connected component is complete if and only if the number of outgoing edges for each component's node is equal to m-1,
   * where m is the number of nodes in the component.
   * 
   * For the same I've used union-find to find the connected components. And then
   * for each component, we check if the number of edges on each node is equal to m-1.
   * 
   * BFS/DFS can also be used to find the connected components and then again check
   * if the number of edges on each node is equal to m-1.
   * 
   * TC: O(n * log n) SC: O(n)
   * #graph #dfs #bfs #union-find #medium
   */

  public int countCompleteComponents(int n, int[][] edges) {
    List<Integer> []adj = new List[n];
    for(int i=0; i<n; i++){
      adj[i] = new LinkedList<>();
    }
    DisjointSet ds = new DisjointSet(n);
    for(int []edge: edges){
      ds.union(edge[0], edge[1]);
      adj[edge[0]].add(edge[1]);
      adj[edge[1]].add(edge[0]);
    }
    Map<Integer, Integer> reps = new HashMap<>();
    Set<Integer> cc = new HashSet<>();
    for(int i=0; i<n; i++) {
      int rep = ds.find(i);
      reps.put(rep, reps.getOrDefault(rep,0)+1);
      cc.add(rep);
      int repcnt = reps.get(rep);
      if(repcnt != adj[i].size()+1 && cc.contains(rep)) cc.remove(rep);
    }
    return cc.size();
  }

  class DisjointSet {
    int[] parent, size;

    public DisjointSet(int n) {
      parent = new int[n];
      size = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
    }

    public int find(int a) {
      if (parent[a] != a) {
        parent[a] = find(parent[a]);
      }
      return parent[a];
    }

    public void union(int a, int b) {
      int rootA = find(a);
      int rootB = find(b);
      if (rootA != rootB) {
        if (size[rootA] < size[rootB]) {
          parent[rootA] = rootB;
          size[rootB] += size[rootA];
        } else {
          parent[rootB] = rootA;
          size[rootA] += size[rootB];
        }
      }
    }
  }
}