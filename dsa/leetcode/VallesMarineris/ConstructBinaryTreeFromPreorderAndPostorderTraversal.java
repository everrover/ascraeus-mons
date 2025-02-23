package dsa.leetcode.VallesMarineris;

// Class definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/description/?envType=daily-question&envId=2025-02-23
     *
     * Reconstruct the binary tree from its preorder and postorder traversal arrays.
     * Identify the position of each node in the postorder array to help split the preorder array into left and right subtrees.
     * 
     * TC: O(n) SC: O(n)
     * #tree #binary-tree #divide-and-conquer #medium
     */
    
    private TreeNode construct(int st, int en, int postst, int posten, int []preorder, int []idxpostorder) {
        if (st > en) return null;
        else if (st == en) return new TreeNode(preorder[st]);
        int nleft = idxpostorder[preorder[st + 1]] - postst + 1;
        TreeNode root = new TreeNode(preorder[st]);
        root.left = construct(st + 1, st + nleft, postst, preorder, idxpostorder);
        root.right = construct(st + nleft + 1, en, postst + nleft, preorder, idxpostorder);
        return root;
    }
}