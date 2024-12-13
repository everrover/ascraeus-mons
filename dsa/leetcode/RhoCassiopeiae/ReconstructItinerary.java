package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class ReconstructItinerary {

  /**
   * https://leetcode.com/problems/reconstruct-itinerary/
   *
   * The problem's def is to perform an Eulerian path traversal on a graph of flights, starting from JFK and visiting all nodes.
   * So did just that... using a depth-first search (DFS) approach, utilizing a map to maintain adjacency lists for each airport
   * To ensure airports are visited in lexical order[greedy choice], a priority queue is used. Could've used a sorted list as well.
   *
   * TC: O(E*log(E)) where E is the number of tickets
   * SC: O(E)
   * #dfs #graph #eulerian-path #hard #greedy
   */

  private void dfs(String curr, Map<String, Boolean> mkV, Map<String, Queue<String>> adjList, final List<String> path){
    if(mkV.get(curr)) return;
    // Traverse each destination in the current airport's adjacency list
    while(!adjList.get(curr).isEmpty()){
      String dest = adjList.get(curr).poll();
      dfs(dest, mkV, adjList, path);
    }
    mkV.put(curr, true);
    path.add(curr);
    return;
  }

  public List<String> findItinerary(List<List<String>> tickets) {
    Map<String, Boolean> mkV = new HashMap<>();
    Map<String, Queue<String>> adjList = new HashMap<>();
    for(List<String> ticket: tickets){
      String a = ticket.get(0);
      String b = ticket.get(1);
      adjList.putIfAbsent(a, new PriorityQueue<>());
      adjList.putIfAbsent(b, new PriorityQueue<>());
      mkV.putIfAbsent(a, false);
      mkV.putIfAbsent(b, false);
      adjList.get(a).add(b);
    }
    List<String> res = new ArrayList<>();
    dfs("JFK", mkV, adjList, res);
    Collections.reverse(res);  // The order of the path needs to be reversed
    return res;
  }

}