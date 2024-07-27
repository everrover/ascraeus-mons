package dsa.leetcode.kuiperBelt;

import java.util.HashSet;
import java.util.Set;

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}

public class LowestCommonAncestorOfABinaryTreeIII {
  /**
   * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/submissions/
   *
   * To find the lowest common ancestor (LCA) of two nodes in a binary tree, we start by finding the root.
   * Using depth-first search (DFS), we traverse the tree from the root,
   * and check if either node matches the current node or is an ancestor of the found path.
   * If both nodes are found along the path from the root, the current node is the LCA.
   *
   * TC: O(n) SC: O(n)
   * #binary-tree #dfs #lca #medium
   */
  Set<Node> visited = new HashSet<>();
  Node res = null;

  public Node lowestCommonAncestor(Node p, Node q) {
    Node root = p;
    // Find the root of the tree
    while (root.parent != null) {
      root = root.parent;
    }
    // Check for LCA from root
    dfs(root, p, q);
    return res;
  }

  private boolean dfs(Node current, Node p, Node q) {
    if (current == null || visited.contains(current)) {
      return false;
    }
    visited.add(current);
    boolean left = dfs(current.left, p, q);
    boolean right = dfs(current.right, p, q);
    if (left || right) {
      if ((current == p || current == q) && res == null) {
        res = current;
      } else if (left && right && res == null) {
        res = current;
      }
      return true;
    }
    return (current == p || current == q);
  }
}