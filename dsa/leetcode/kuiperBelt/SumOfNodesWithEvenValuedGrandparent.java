package dsa.leetcode.KuiperBelt;

import dsa.leetcode.internals.TreeNode;

/**
 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
 *
 * This method uses depth-first search to traverse the tree while keeping track of the evenness of parent and grandparent nodes.
 * Whenever it finds a node with an even-valued grandparent, it adds the value of this node to the sum.
 * 
 * TC: O(n) since it traverses each node once. SC: O(h) where h is the height of the tree, due to recursion stack.
 *
 * #tree #depth-first-search #breadth-first-search #binary-tree #medium
 */
public class SumOfNodesWithEvenValuedGrandparent {
  public int sumEvenGrandparent(TreeNode root) {
    return dfs(root, false, false);
  }

  private int dfs(TreeNode root, boolean isParentEven, boolean isGPEven){
    if(root == null) return 0;
    int left = dfs(root.left, root.val % 2 == 0, isParentEven);
    int right = dfs(root.right, root.val % 2 == 0, isParentEven);
    return (isGPEven ? root.val : 0) + left + right;
  }
}