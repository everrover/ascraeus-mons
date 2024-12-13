package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class ReconstructItinerary {

  /**
   * https://leetcode.com/problems/reconstruct-itinerary/
   *
   * The task is to find an Eulerian path in a graph of flights, starting from JFK and visiting all nodes.
   * We use a depth-first search (DFS) approach, utilizing a map to maintain adjacency lists for each airport
   * and a priority queue to ensure airports are visited in lexical order.
   * 
   * TC: O(E + V log V) SC: O(E + V)
   * #dfs #graph #eulerian-circuit #hard
   */

  private void dfs(String curr, Map<String, Boolean> mkV, Map<String, Queue<String>> adjList, final List<String> path) {
    if (mkV.get(curr)) return;
    while (!adjList.get(curr).isEmpty()) {
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
    for (List<String> ticket: tickets) {
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
    Collections.reverse(res);
    return res;
  }
}