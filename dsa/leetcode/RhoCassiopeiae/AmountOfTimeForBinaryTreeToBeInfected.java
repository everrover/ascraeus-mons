package dsa.leetcode.RhoCassiopeiae;

/**
 * https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
 *
 * Another possible solution:
 * Convert the tree to an undirected graph to make it easier to handle.
 * Use DFS/BFS starting at the start node to find maximum distance from the start node to any other node
 * 
 * ❗️ Needed help at it... for optimal post-order traversal solution
 *
 * TC: O(n), SC: O(n)
 * #tree #graph #bfs #medium #post-order-traversal #dfs
 */

public class AmountOfTimeForBinaryTreeToBeInfected {
  
  /**
   * Definition for a binary tree node.
   */
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  class Solution {
    private int maxDist = 0;
    
    private int traverse(TreeNode root, int start) {
      if (root == null) return 0;
      int leftdepth = traverse(root.left, start);
      int rightdepth = traverse(root.right, start);
      int depth = 0;
      if (root.val == start) { // one possible solution, max depth of the tree under infected node
        depth = -1;
        maxDist = Math.max(leftdepth, rightdepth);
      } else if (leftdepth >= 0 && rightdepth >= 0) { // all nodes below aren't infected
        depth = Math.max(leftdepth, rightdepth) + 1;
      } else { // one of the nodes is infected 
        // - -ve values are the depth of the infected node
        // - combined with the other subtree, we get the max distance
        // ./AmountOfTimeForBinaryTreeToBeInfected.jpeg
        maxDist = Math.max(maxDist, Math.abs(leftdepth) + Math.abs(rightdepth));
        depth = Math.min(leftdepth, rightdepth) - 1;
      }
      return depth;
    }
  
    public int amountOfTime(TreeNode root, int start) {
      traverse(root, start);
      var res = 0;
      return res = maxDist;
    }
  }
}