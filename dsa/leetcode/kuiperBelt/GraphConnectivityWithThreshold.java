package dsa.leetcode.kuiperBelt;

import java.util.*;

/**
 * https://leetcode.com/problems/graph-connectivity-with-threshold/
 * This solution uses Union-Find data structure to determine if two cities are connected.
 * The cities are considered connected if their labels share a common divisor greater than a given threshold.
 * Sieve of Eratosthenes is used to find the next possible common divisors.
 * 
 * I made a mistake in using all the divisors of a number to connect the cities. Corrected it afterwards.
 * 
 * TC: O(n log(log(n)) + q*alpha(n)) where n is the number of cities and q is the number of queries.
 * SC: O(n) for the Union-Find data structure.
 * #array #math #union-find #number-theory #hard #sieve-of-eratosthenes
 */

class GraphConnectivityWithThreshold {

  int[] rank;
  int[] parent;

  int findSet(int v) {
    if (v == parent[v]) return v;
    return parent[v] = findSet(parent[v]);
  }

  private void unionSet(int a, int b) {
    a = findSet(a);
    b = findSet(b);
    if (a != b) {
      if (rank[a] < rank[b])
        parent[a] = b;
      else
        parent[b] = a;
      if (rank[a] == rank[b])
        rank[a] = rank[a]+1;
    }
  }

  public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
    this.rank = new int[n+1];
    this.parent = new int[n+1];
    for(int i=1; i<=n; i++){
      parent[i] = i;
      rank[i] = 0;
    }
    for (int z = threshold + 1; z <= n; z++)
      for (int x = z * 2; x <= n; x += z)
        unionSet(z, x);
    List<Boolean> res = new LinkedList<>();
    for(int []query: queries){
      res.add(findSet(query[0]) == findSet(query[1]));
    }
    return res;
  }
}