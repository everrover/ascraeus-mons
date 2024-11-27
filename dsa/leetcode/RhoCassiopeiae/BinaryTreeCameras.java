package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-cameras/
 *
 * To minimize the number of cameras needed to monitor all nodes in a binary tree, use dynamic programming combined
 * with depth-first search. Track states for node monitoring: 0(not covered), 1(covered, no camera), and 2(camera on node
 * or immediate child). Through recursive calculation, determine the minimum number of cameras needed depending on
 * these states.
 *
 * TC: O(n) SC: O(n)
 * #tree #dfs #dynamic-programming #hard
 */

public class Solution {
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
      res = dfs(root.left, 0) + dfs(root.right, 0);
      res = Math.min(res, 1 + dfs(root.left, 1) + dfs(root.right, 1));
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