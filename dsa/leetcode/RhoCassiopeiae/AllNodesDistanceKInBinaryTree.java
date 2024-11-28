package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 *
 * It's simple post-order traversal with depth tracking. If `right` subtree has target node,
 * then we do the same traversal on `left` subtree to add resulting nodes. Vice-versa case is handled by default.
 * In worst case the tree would be traversed twice, no more.
 * 
 * I also thought of some solutions where a graph was constructed from the tree and then BFS/DFS was applied
 * to find the nodes at distance `k`. I was able to do the same with just the post-order tree traversal.
 *
 * TC: O(n) SC: O(n)
 * #binary-tree #tree #dfs #bfs #binary-traversals #bfs #medium
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    List<Integer> res = new LinkedList<>();
    private int k;
    private int postOrder(TreeNode root, TreeNode target, boolean ptfound, int ptdepth) {
        if (root == null) return -1;

        int tdepth = -1;
        if (root == target) {
            ptfound = true;
            ptdepth = 0;
            tdepth = 1;
        }

        if (ptfound) {
            if (ptdepth == k) res.add(root.val);
            else if (ptdepth > k) return -1;
        }

        int ltdepth = postOrder(root.left, target, ptfound, ptdepth + (ptfound ? 1 : 0));
        if (ltdepth != -1) {
            tdepth = Math.max(tdepth, 1 + ltdepth);
            if (ltdepth == k) res.add(root.val);
            postOrder(root.right, target, true, 1 + ltdepth);
        } else {
            int rtdepth = postOrder(root.right, target, ptfound, ptdepth + (ptfound ? 1 : 0));
            if (rtdepth != -1) {
                if (rtdepth == k) res.add(root.val);
                tdepth = Math.max(tdepth, 1 + rtdepth);
                postOrder(root.left, target, true, 1 + rtdepth);
            }
        }

        return tdepth;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        this.k = k;
        postOrder(root, target, false, 0);
        return res;
    }
}