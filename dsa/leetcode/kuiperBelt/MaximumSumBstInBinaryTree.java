package dsa.leetcode.kuiperBelt;

class TreeNode {
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

public class MaximumSumBstInBinaryTree {
    /**
     * https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
     * 
     * To find the maximum sum of all keys of any subtree which is also a Binary Search Tree (BST), we use a post-order
     * tree traversal to determine if each subtree is a BST and calculate the sum of its nodes. We maintain a current
     * maximum sum during the traversal. For each node, compute parameters such as the sum, and the minimum and maximum
     * values within the subtree to check if it qualifies as a BST.
     * 
     * TC: O(n) SC: O(n)
     * #dynamic-programming #tree #depth-first-search #binary-search-tree #binary-tree #hard
     */

    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        postOrderTraverse(root);
        return maxSum;
    }

    private int[] postOrderTraverse(TreeNode root) {
        if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}; // {min, max, sum}, initialize min=MAX_VALUE, max=MIN_VALUE
        int[] left = postOrderTraverse(root.left);
        int[] right = postOrderTraverse(root.right);

        // Check if the current tree rooted at `root` is a BST
        if (!(left != null                   // the left subtree must be BST
                && right != null               // the right subtree must be BST
                && root.val > left[1]          // the root's key must be greater than maximum keys of the left subtree
                && root.val < right[0]))       // the root's key must be lower than minimum keys of the right subtree
            return null;

        int sum = root.val + left[2] + right[2]; // now it's a BST, make `root` as root
        maxSum = Math.max(maxSum, sum);          // update maxSum if the current subtree sum is greater
        int min = Math.min(root.val, left[0]);   // find the minimum value in the current subtree
        int max = Math.max(root.val, right[1]);  // find the maximum value in the current subtree
        return new int[]{min, max, sum};         // return the parameters of the current subtree
    }
}