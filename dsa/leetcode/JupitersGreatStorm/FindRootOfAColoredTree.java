package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/find-root-of-a-colored-tree/
 *
 * Prior to the following approach, I just used Brute Force BFS from every node, checking if the
 * layered pattern was satisfied, iff a node had a degree <= 2.
 * 
 * Surprising, the problem allows an O(n²) solution, since the number of degree<=2 candidates is 
 * at most n, and each BFS is O(n). i.e. for sth like, we'd still try for many candidates
 * R -> B -> W -> R -> B -> W -> R -> B -> W -> R -> B -> W
 * 
 * THIS clicked in morning, as I woke up.
 * Forced edge orientation. Every parent-to-child edge must advance exactly one step in the cycle
 * R -> B -> W -> R, and so this entails. Each edge's direction is fixed by its endpoint colours alone:
 * - If colour(v) is the successor of colour(u) the edge must be u -> v (and vice versa); an edge
 *   with equal-coloured endpoints admits neither direction and is dropped, which disconnects the
 *   directed graph and makes every BFS fail.
 * - A valid root must therefore have indegree 0 (nothing may point into it) and outdegree <= 2
 *   (binary property). Non-root nodes automatically have at most two children, since their degree
 *   is at most 3 and one edge goes to the parent.
 * - On a tree, the root is valid iff following the forced directions from it reaches all n nodes:
 *   then every edge points away from it, so the colour at depth d is the d-th successor of
 *   colour(root), which is exactly the layered pattern. A BFS from each candidate verifies this; tree acyclicity
 *   guarantees each node is enqueued at most once.
 *
 * TC: O(n·k), k = indegree-0 candidates (O(n²) worst case) SC: O(n)
 * #tree #graph #breadth-first-search #medium
 */

class FindRootOfAColoredTree {

  private void addNode(int u, int v, int [][]em, int []indegree, int []outdegree, char[] colors){
    if(
      (colors[u] == 'R' && colors[v] == 'B') || 
      (colors[u] == 'B' && colors[v] == 'W') || 
      (colors[u] == 'W' && colors[v] == 'R')){
      if(em[u][0] == -1) em[u][0] = v;
      else if(em[u][1] == -1) em[u][1] = v;
      else em[u][2] = v;
      outdegree[u]++; indegree[v]++;
    }else if(
      (colors[v] == 'R' && colors[u] == 'B') || 
      (colors[v] == 'B' && colors[u] == 'W') || 
      (colors[v] == 'W' && colors[u] == 'R')){
      if(em[v][0] == -1) em[v][0] = u;
      else if(em[v][1] == -1) em[v][1] = u;
      else em[v][2] = u;
      indegree[u]++; outdegree[v]++;
    }
  }
  public int findColoredRoot(int[][] edges, String colors) {
    int n = colors.length();
    char []colarr = colors.toCharArray();
    int []indegree = new int[n];
    int []outdegree = new int[n];
    int [][]edgemap = new int[n][3];
    for(int i=0; i<n; i++) Arrays.fill(edgemap[i], -1);
    for(int []edge: edges) {
      addNode(edge[0], edge[1], edgemap, indegree, outdegree, colarr);
    }
    for(int i=0; i<n; i++) if(outdegree[i]<=2 && indegree[i]==0 && bfs(edgemap, i)) return i;
    return -1;
  }
  private boolean bfs(int[][] v, int start){
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    int marked = 0;
    boolean []mark = new boolean[v.length];
    while(!q.isEmpty()){
      int lsize = q.size();
      while(lsize-->0 && !q.isEmpty()){
        int node = q.poll();
        mark[node] = true;
        marked++;
        if(v[node][0] != -1 && !mark[v[node][0]]) q.offer(v[node][0]);
        if(v[node][1] != -1 && !mark[v[node][1]]) q.offer(v[node][1]);
        if(v[node][2] != -1 && !mark[v[node][2]]) q.offer(v[node][2]);
      }
    }
    return marked==v.length;
  }
}
