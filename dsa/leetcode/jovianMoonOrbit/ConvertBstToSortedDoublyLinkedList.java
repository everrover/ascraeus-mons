package dsa.leetcode.JovianMoonOrbit;

// Definition for a Node.

/**
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 *
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 * In the transformation, the left pointer of the tree node points to its predecessor,
 * and the right pointer points to its successor.
 *
 * TC: O(n) SC: O(h), where n is the number of nodes, and h is the height of the tree.
 * #linked-list #tree #binary-search-tree #medium
 * 
 * p.s. did this problem in university...
 */
class Solution {

    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        Node[] res = postorder(root);
        res[0].left = res[1]; // Connect the smallest element with the largest element
        res[1].right = res[0]; // Connect the largest element with the smallest element
        return res[0]; // Return the smallest element
    }

    private Node[] postorder(Node root) {
        if (root == null) return null;
        Node[] l = postorder(root.left); // Process left subtree
        Node[] r = postorder(root.right); // Process right subtree
        if (l != null) { l[1].right = root; root.left = l[1]; } // Connect left subtree
        if (r != null) { r[0].left = root; root.right = r[0]; } // Connect right subtree
        return new Node[] { l != null ? l[0] : root, r != null ? r[1] : root }; // Return the smallest and largest
    }
}