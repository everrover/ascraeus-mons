package dsa.leetcode.kuiperBelt;

import java.util.*;

public class VerticalOrderTraversalOfBinaryTree {
    /**
     * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
     * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
     * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively.
     * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column.
     * Nodes on the same row and column are sorted by their values.
     * 
     * TC: O(n) SC: O(n)
     * #hash-table #tree #dfs #bfs #sorting #binary-tree #hard
     */

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<Integer>[] al = new List[2001]; // Initialize list array
        for (int i = 0; i <= 2000; i++) al[i] = new LinkedList<>();
        dfs(root, al);
        List<List<Integer>> res = new LinkedList<>(); // Result list
        for (List<Integer> ll : al) if (!ll.isEmpty()) res.add(ll);
        return res;
    }

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static class T{
        public int i;
        public TreeNode n;
        public T(int idx, TreeNode node){
            i = idx;
            n = node;
        }
    }

    private void dfs(TreeNode root, List<Integer>[] al) { // Perform DFS
        if (root == null) return;
        Queue<T> q = new LinkedList<>(); // Queue for BFS
        q.offer(new T(0, root)); // Offer root node
        Map<Integer, List<Integer>> tm = new HashMap<>(); // Temporary map to hold nodes
        while (!q.isEmpty()) {
            int n = q.size(); // Number of nodes in current level
            tm.clear(); // Clear temporary map
            while (n-- > 0) {
                T t = q.poll(); // Dequeue node
                tm.putIfAbsent(t.i, new ArrayList<>());
                tm.get(t.i).add(t.n.val); // Add the node's value to the map
                if (t.n.left != null) q.offer(new T(t.i - 1, t.n.left)); // Left child
                if (t.n.right != null) q.offer(new T(t.i + 1, t.n.right)); // Right child
            }
            for (Map.Entry<Integer, List<Integer>> me : tm.entrySet()) {
                Collections.sort(me.getValue()); // Sort the values
                al[me.getKey() + 1000].addAll(me.getValue()); // Add sorted values to the list
            }
        }
    }
}