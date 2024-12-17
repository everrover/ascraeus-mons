package dsa.leetcode.RhoCassiopeiae;

/** 
 * https://leetcode.com/problems/node-with-highest-edge-score/
 * 
 * Node with the highest edge score is found by iterating over each edge and accumulating scores. 
 * If a tie occurs, the node with the smaller index is selected.
 * 
 * ❗️The score selection coul've been done by a separate for-loop... interview discussion on top
 * of which to build the current
 * 
 * TC: O(n) SC: O(n)
 * #hash-table #graph #medium
 */

class Solution {
  public int edgeScore(int[] edges) {
    // Array to accumulate edge scores
    long[] a = new long[edges.length];
    int resi = 0; // Store result index
    for (int i = 0; i < edges.length; i++) {
      // Accumulate edge scores
      a[edges[i]] += i;
      // Update result index if new highest score or smaller index at tie
      // a[edges[i]] and a[resi] are only the impacted edges, so i'th score isn't considered
      if (a[edges[i]] > a[resi] || (a[edges[i]] == a[resi] && edges[i] < resi))
        resi = edges[i];
    }
    return resi;
  }
}