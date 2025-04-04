package dsa.leetcode.VallesMarineris;

import java.util.*;

public class LowestCommonAncestorOfDeepestLeaves {

  /**
   * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/?envType=daily-question&envId=2025-04-04
   *
   * Perform a postorder traversal to determine the depth and then compare depths of left and right children.
   * If both children have the deepest leaf, the current node is marked as the answer.
   *
   * TC: O(n) SC: O(h), where h is the height of the tree
   * #tree #dfs #binary-tree #medium
   */

  // Custom class for returning the TreeNode and its maximum depth
  private class T {
    TreeNode node;
    int md; // max depth

    T(TreeNode node, int md) {
      this.node = node;
      this.md = md;
    }
  }

  public TreeNode lcaDeepestLeaves(TreeNode root) {
    return dfs(root, 0).node;
  }

  private T dfs(TreeNode root, int depth) {
    if(root == null) return new T(root, depth);
    T left = dfs(root.left, depth+1);
    T right = dfs(root.right, depth+1);
    if(left.md > right.md) return left;
    if(right.md > left.md) return right;
    return new T(root, left.md);
  }
}