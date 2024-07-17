package dsa.leetcode.kuiperBelt;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 *
 * https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/
 * This solution recursively computes sum and count of nodes for each subtree to calculate average.
 * It then compares the average with the root node value of the current subtree.
 * 
 * TC: O(n) SC: O(h) where n is the number of nodes and h is the height of the tree
 * #tree #dfs #binary-tree #medium
 */

class Solution {
  public int averageOfSubtree(TreeNode root) {
    int []avg = dfs(root);
    return avg[2];
  }
  
  private int[] dfs(TreeNode root){
    if(root == null) return new int[]{0,0,0};
    int []left = dfs(root.left);
    int []right = dfs(root.right);
    int sum = left[1]+right[1]+root.val;
    int cnt = left[0]+right[0]+1;
    int avg = sum/cnt;
    return new int[]{cnt, sum, left[2]+right[2]+(avg==root.val?1:0)};
  }
}