package dsa.leetcode.KuiperBelt;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-arrangement-of-pairs/
 *
 * Hierholzer’s algorithm for finding an Eulerian path is applied.
 *
 * Counts the difference between in-degree and out-degree for each node to find the start of the Eulerian path.
 *
 * In context of interview, three constraints can be *removed* one by one to make the problem tricky.
 * - Valid pairs are given => compute indegree and outdegree of each node and validate if the graph is Eulerian
 *                         => perform DFS to find the Eulerian path and no nodes are 'unvisited'(Sparse graph)
 * - Duplicate pairs aren't allowed => compute indegree and outdegree validations of each node by clubbing such nodes together
 *                                  => perform DFS to find the Eulerian path but while treating the duplicate pairs as a single
 *                                  distinct pair and find the longest path while allowing the duplicate nodes to be
 *                                  visited as many times as they are present in the input
 * - start[i] != end[i-1] => treat all such nodes as just one node, since they can be clubbed as single seq
 *                          a,b;b,b;b,b;b,b;b,c;c,d;d,b;b,e => a,b;b,c;c,d;d,b;b,e, now b,b;3 can be fit in the middle of ab,bc or db,be
 * 
 * TC: O(n) SC: O(n)
 *
 * #depth-first-search #graph #eulerian-circuit #hard
 */

class ValidArrangementOfPairs {
  private int aidx = 0;
  public int[][] validArrangement(int[][] pairs) {
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Stack<Integer>> adj = new HashMap<>();
    
    for(int []p: pairs){
      map.putIfAbsent(p[0], 0);
      map.putIfAbsent(p[1], 0);
      map.put(p[0], map.get(p[0])+1);
      map.put(p[1], map.get(p[1])-1);
      adj.putIfAbsent(p[0], new Stack<>());
      adj.putIfAbsent(p[1], new Stack<>());
      adj.get(p[0]).push(p[1]);
    }
    
    int sn = -1;
    // if more than 1 node with in-degree+1 == out-degree and out-degree+1 == in-degree, then it's not possible
    for(Map.Entry<Integer, Integer> e: map.entrySet()) if(e.getValue() == 1) sn = e.getKey();
    
    if(sn == -1) { // if closed circuit
      sn = pairs[0][0];
    }
    int[][] ans = new int[pairs.length][2];
    dfs(ans, adj, sn);
    // reverse
    for(int i=0; i<ans.length/2; i++){
      int []tmp = ans[i];
      ans[i] = ans[ans.length-1-i];
      ans[ans.length-1-i] = tmp;
    }
    return ans;
  }
  
  private void dfs(int [][]ans, Map<Integer, Stack<Integer>> adj, int curr){ // Stack is just for shorthand coding
    Stack<Integer> st = adj.get(curr);
    while(!st.isEmpty()){
      int ni = st.pop();
      dfs(ans, adj, ni);
      ans[aidx++] = new int[]{curr, ni}; // add pair here to accommodate the longest path greedily
    }
  }
}