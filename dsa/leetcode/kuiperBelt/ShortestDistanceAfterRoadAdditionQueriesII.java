package dsa.leetcode.kuiperBelt;

import java.util.TreeSet;

public class ShortestDistanceAfterRoadAdditionQueriesII {

  /**
   * https://leetcode.com/problems/shortest-distance-after-road-addition-queries-ii/
   * 
   * The solution uses a TreeSet to maintain the cities from 0 to n - 1. We process each query by removing intermediate
   * cities from the set.
   * This approach guarantees that the shortest path between any two given points is maintained as minimal.
   * Why? Because of constraint, `There are no two queries such that i != j and queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1]`
   * No crossing queries are possible.
   * x0, x1, y0, y1.
   * x0, y0, y1, x1.
   * y0, y1, x0, x1.
   * y0, x0, x1, y1.
   *
   * Are the only possible queries.
   *
   * Earlier I was trying to solve it using a segment tree on edges. But, it was utterly stupid.
   *
   * p.s. we can also use LinkedList[not from STL] as a graph with HashMap to store references to the nodes. or we can
   * perform merge operation on the intervals using node-start and node-end values
   * 
   * TC: O(q • log(n)) SC: O(n)
   * #graph #shortest-path #greedy #hard #sneaky-constraint
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
  /*
  private static class TN{
      public int st, en, score;
      public boolean isLeaf;
      private TN left, right;
      private TN(int s, int e, int sc){
        st = s; en = e; score = sc;
        left = right = null; isLeaf = false;
      }

      private static TN build(int ss, int se){
        if(ss > se) return null;
        if(ss == se){
          TN node = new TN(ss, se, 1);
          node.isLeaf = true;
          return node;
        }else{
          int mid = ss + (se-ss)/2;
          TN left = build(ss,mid), right = build(mid+1, se);
          TN node = new TN(left.st, right.en, left.score+right.score);
          node.score = left.score + right.score;
          node.left = left; node.right = right;
          return node;
        }
      }

      public static void update(int qs, int qe, TN curr){
        if(curr == null || qs > curr.en || qe < curr.st) return;
        else if(qs <= curr.st && qe >= curr.en){
          curr.isLeaf = true; curr.score = 1; curr.left = curr.right = null;
        } else{
          if(curr.isLeaf) return;
          update(qs, qe, curr.left);
          update(qs, qe, curr.right);
          int score = 0;
          if(curr.left != null) score += curr.left.score;
          if(curr.right != null) score += curr.right.score;
          curr.score = score;
        }
      }

      public static TN build(int size){
        return build(0, size-1);
      }
    }
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
      TN root = TN.build(n-1);
      int res[] = new int[queries.length];
      int i = 0;
      for(int []q: queries){
        root.update(q[0], q[1]-1, root);
        res[i++] = root.score;
      }
      return res;
    }
   */
}