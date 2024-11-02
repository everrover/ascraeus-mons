package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class BusRoutes {
  /**
   * https://leetcode.com/problems/bus-routes/
   *
   * Use a Breadth-First Search (BFS) approach, modeling as a graph where nodes are bus stops and edges are direct bus routes.
   * Build a map from bus stops to bus routes to efficiently find possible paths from `source` to `target`.
   *
   * TC: O(n * m), SC: O(n * m) where n is the number of routes and m is the average number of stops per route
   * #array #hash-table #breadth-first-search #hard
   */

  public int numBusesToDestination(int[][] routes, int source, int target) {
    if (source == target) return 0;  // If starting and target points are the same
    
    Map<Integer, Set<Integer>> m = new HashMap<>();  // Maps bus stops to potential bus lines

    // Build the map
    for (int i = 0; i < routes.length; i++) {
      for (int r : routes[i]) {
        m.putIfAbsent(r, new HashSet<>());
        m.get(r).add(i);
      }
    }

    // Check if routes from source or to target are missing
    if (!m.containsKey(target) || !m.containsKey(source)) return -1;

    Set<Integer> markB = new HashSet<>();  // Mark buses taken
    Set<Integer> markS = new HashSet<>();  // Mark stops visited
    Queue<Integer> q = new LinkedList<>();  // BFS queue for bus stops
    q.offer(source);
    int res = 0;
    
    // BFS through bus stop routes
    while (!q.isEmpty()) {
      int sz = q.size();
      while (sz-- > 0) {
        int busstop = q.poll();
        if (target == busstop) return res;

        for (int busnew : m.get(busstop)) {
          if (markB.contains(busnew)) continue;  // Skip if this bus has been taken

          for (int busstopnew : routes[busnew]) {
            if (markS.contains(busstopnew)) continue;  // Skip if this stop has been visited

            q.offer(busstopnew);
            markS.add(busstopnew);
          }
          markB.add(busnew);
        }
      }
      res++;
    }
    return -1;  // Return -1 if target is unreachable
  }
}