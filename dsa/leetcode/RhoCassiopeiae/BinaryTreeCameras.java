package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-cameras/
 *
 * 0 = not under surveillance, 1 = under surveillance, 2 = parent not under surveillance
 * - if current node is not under surveillance, then either of the children must place a camera 
 * - if cuurent node is under surveillance, then either children can choose to place a camera or not
 * - if parent is not under surveillance, then current node 'must' place a camera
 * or current node must place a camera
 * 
 * TODO: because my solution is naive
 * - LAYER BY LAYER DP DFS(height, state) instead of DFS(node, state) - https://leetcode.com/problems/binary-tree-cameras/discuss/211223/C%2B%2B-Naive-DFS-%2B-Memo
 * - GREEDY SOLUTION - https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS
 *
 * TC: O(n) SC: O(n)
 * #tree #dfs #dynamic-programming #hard
 */

public class BinaryTreeCameras {
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

  private Map<TreeNode, Integer> ti;
  private int[][] dp;
  private int count = 0;

  private int dfs(TreeNode root, int camen) {
    if (root == null) return camen == 2 ? 1 : 0;

    if (!ti.containsKey(root))
      ti.put(root, count++);
    int idx = ti.get(root);

    if (dp[idx][camen] != -1) return dp[idx][camen];

    int res = 1000;
    if (camen == 0) {
      res = 1 + dfs(root.left, 1) + dfs(root.right, 1);
      res = Math.min(res, dfs(root.left, 0) + dfs(root.right, 2));
      res = Math.min(res, dfs(root.left, 2) + dfs(root.right, 0));
    } else if (camen == 1) {
      res = dfs(root.left, 0) + dfs(root.right, 0); // don't place camera here
      res = Math.min(res, 1 + dfs(root.left, 1) + dfs(root.right, 1)); // place camera here
    } else if (camen == 2) {
      res = 1 + dfs(root.left, 1) + dfs(root.right, 1);
    }
    return dp[idx][camen] = res;
  }

  public int minCameraCover(TreeNode root) {
    dp = new int[1000][3];
    for (int[] d : dp) Arrays.fill(d, -1);
    count = 0;
    ti = new HashMap<>();
    // 0=not under survey 1=under survey 2=parent not under survey
    return Math.min(dfs(root, 2), dfs(root, 0));
  }
}
/*
 * TODO: Greedy solution
 * Intuition:
Consider a node in the tree.
It can be covered by its parent, itself, its two children.
Four options.


Consider the root of the tree.
It can be covered by left child, or right child, or itself.
Three options.


Consider one leaf of the tree.
It can be covered by its parent or by itself.
Two options.


If we set a camera at the leaf, the camera can cover the leaf and its parent.
If we set a camera at its parent, the camera can cover the leaf, its parent and its sibling.


We can see that the second plan is always better than the first.
Now we have only one option, set up camera to all leaves' parent.


Here is our greedy solution:


Set cameras on all leaves' parents, thenremove all covered nodes.
Repeat step 1 until all nodes are covered.
Explanation:
Apply a recusion function dfs.
Return 0 if it's a leaf.
Return 1 if it's a parent of a leaf, with a camera on this node.
Return 2 if it's coverd, without a camera on this node.


For each node,
if it has a child, which is leaf (node 0), then it needs camera.
if it has a child, which is the parent of a leaf (node 1), then it's covered.


If it needs camera, then res++ and we return 1.
If it's covered, we return 2.
Otherwise, we return 0.

```
int res = 0;
    public int minCameraCover(TreeNode root) {
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }
```
 */