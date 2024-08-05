package dsa.leetcode.kuiperBelt;

import java.util.TreeSet;

public class ShortestDistanceAfterRoadAdditionQueriesII {

  /**
   * https://leetcode.com/problems/shortest-distance-after-road-addition-queries-ii/
   * 
   * The solution uses a TreeSet to maintain the cities from 0 to n - 1. We process each query by removing intermediate cities from the set.
   * This approach guarantees that the shortest path between any two given points is maintained as minimal.
   * 
   * TC: O(q • log(n)) SC: O(n)
   * #graph #shortest-path #greedy #hard
   */
  
  public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
    TreeSet<Integer> bst = new TreeSet<>();
    for (int i = 1; i < n - 1; i++) {
      bst.add(i);
    }
    int res[] = new int[queries.length];
    int i = 0;
    for (int[] q : queries) {
      while (bst.higher(q[0]) != null && bst.higher(q[0]) < q[1]) { 
        bst.remove(bst.higher(q[0]));
      }
      res[i++] = bst.size() + 1;
    }
    return res;
  }
}