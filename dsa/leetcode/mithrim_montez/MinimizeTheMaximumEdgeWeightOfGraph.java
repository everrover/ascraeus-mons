package dsa.leetcode.mithrim_montez;

import java.util.*;

public class MinimizeTheMaximumEdgeWeightOfGraph {
  
  /**
   * https://leetcode.com/problems/minimize-the-maximum-edge-weight-of-graph/description/
   * 
   * Utilize a binary search approach to find the minimal possible maximum edge weight. 
   * Check feasibility using a modified BFS/DFS approach on reversed graph with a max-weight threshold for outgoing edges.
   * 
   * Since we're going from root through the leaves(rev-graph), there's only one outgoing edge that connects
   * to the parent. So, `threshold` on number of out-going edges is never breached. Infact this was a hint...
   * 
   * Because we're essentially finding a MST, we can use Prim's algorithm to find the minimum spanning tree. And if the
   * MST has all the nodes, and worst case max-edge is not greater than the threshold, then we have the answer.
   * 
   * TC: O(E * log(maxWeight)), where E is the number of edges and maxWeight is the max possible edge weight.
   * SC: O(V + E), where V is the number of nodes and E is the number of edges.
   * #binary-search #graph #bfs #dfs #medium
   */
  
   public boolean bfs(Map<Integer, Integer> []g, final int maxw, final int n){
    boolean []v = new boolean[n];
    Queue<Integer> q = new LinkedList<>();
    q.offer(0);
    int cnt = 0;
    while(!q.isEmpty()){
      int curr = q.poll();
      if(v[curr]) continue;
      cnt++; v[curr] = true;
      for(Map.Entry<Integer, Integer> node: g[curr].entrySet()){
        if(node.getValue() == 0 || v[node.getKey()] ||  node.getValue() > maxw) continue;
        q.offer(node.getKey());
      }
    }
    return cnt==n;
  }

  public int minMaxWeight(int n, int[][] edges, int threshold) {
    int maxw = 0;
    Map<Integer, Integer> []g = new Map[n];
    for(int i=0; i<g.length; i++) {
      g[i] = new HashMap<>();
    }
    for(int []edge: edges){
      g[edge[1]].putIfAbsent(edge[0], Integer.MAX_VALUE);
      g[edge[1]].put(
        edge[0], Math.min(edge[2], g[edge[1]].get(edge[0]))
      );
      maxw = Math.max(edge[2], maxw);
    }
    int l = 1, r = maxw, mid, res = -1;
    while(l<=r){
      mid = (l+r)/2;
      if(bfs(g, mid, n)){
        res = mid;
        r = mid-1;
      }else{
        l = mid+1;
      }
    }

    return res;
  }

  public int minMaxWeightUsingPrims(int n, int[][] edges, int threshold) {
    int maxw = 0;
    Map<Integer, Integer> []g = new Map[n];
    for(int i=0; i<g.length; i++) {
      g[i] = new HashMap<>();
    }
    for(int []edge: edges){
      g[edge[1]].putIfAbsent(edge[0], Integer.MAX_VALUE);
      g[edge[1]].put(
        edge[0], Math.min(edge[2], g[edge[1]].get(edge[0]))
      );
      maxw = Math.max(edge[2], maxw);
    }
    int []v = new int[n];
    Queue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
    pq.offer(new int[]{-1,0});
    int cnt = 0, res = 0;
    while(!pq.isEmpty()){
      int[] curr = pq.poll();
      if(v[curr[1]]!=0) continue;
      cnt++;
      v[curr[1]] = curr[0];
      res = Math.max(curr[0], res);
      for(Map.Entry<Integer, Integer> node: g[curr[1]].entrySet()){
        if(v[node.getKey()]!=0) continue;
        pq.offer(new int[]{node.getValue(), node.getKey()});
      }
    }

    return cnt == n? res: -1;
  }

}