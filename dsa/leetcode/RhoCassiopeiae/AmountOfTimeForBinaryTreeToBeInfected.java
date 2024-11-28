package dsa.leetcode.RhoCassiopeiae;

/**
 * https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
 *
 * Convert the tree to an undirected graph to make it easier to handle.
 * Use BFS starting at the start node to find the distance between each node and the start node.
 * The answer is the maximum distance.
 *
 * TC: O(n), SC: O(n)
 * #tree #graph #bfs #medium
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
      if (root.val == start) {
        depth = -1;
        maxDist = Math.max(leftdepth, rightdepth);
      } else if (leftdepth >= 0 && rightdepth >= 0) { // all nodes connected
        depth = Math.max(leftdepth, rightdepth) + 1;
      } else {
        // Calculate max distance
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