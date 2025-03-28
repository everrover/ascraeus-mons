package dsa.leetcode.VallesMarineris;

import java.util.*;

public class PropertiesGraph {

  /**
   * https://leetcode.com/problems/properties-graph/
   *
   * Construct a graph where nodes are connected if their corresponding property arrays have at least 'k' common elements.
   * Use union-find (Disjoint Set Union) to find the number of connected components in the graph.
   * 
   * Could've used DFS or BFS as well...
   *
   * TC: O(n^2 * m) SC: O(n * m)
   * #array #hash-table #dfs #bfs #union-find #graph #medium
   */

   private static class DisjointSet{
    public int []parent;
    public int []rank;
    DisjointSet(int n){
      parent = new int[n];
      rank = new int[n];
      for(int i=0; i<n; i++){
        parent[i] = i;
      }
    }

    public int find(int x){
      if(parent[x] != x){
        parent[x] = find(parent[x]);
      }
      return parent[x];
    }

    public void union(int x, int y){
      int px = find(x);
      int py = find(y);
      if(px == py) return;
      if(rank[px] > rank[py]){
        parent[py] = px;
      }else{
        parent[px] = py;
        rank[py]++;
      }
    }
  }
  public int numberOfComponents(int[][] properties, int k) {
    final int m = properties.length;
    if(m == 0) return 0;
    final int n = properties[0].length;
    int res = 0;
    DisjointSet ds = new DisjointSet(m);
    Set<Integer> []s = new Set[m];
    for(int i=0; i<m; i++) {
      Set<Integer> hs = new HashSet<>();
      for(int j=0; j<n; j++) hs.add(properties[i][j]);
      s[i] = hs;
    }
    for(int i=0; i<m; i++){
      for(int j=0; j<m; j++) {
        if(i == j) continue;
        Set<Integer> inte = new HashSet<Integer>(s[i]);
        inte.retainAll(s[j]);
        if(inte.size()>=k) ds.union(i,j);
      }
    }
    Set<Integer> inte = new HashSet<Integer>();
    for(int i=0; i<m; i++){
      if(!inte.contains(ds.parent[i])) { res++; inte.add(ds.parent[i]); }
    }
    return res;
  }
}