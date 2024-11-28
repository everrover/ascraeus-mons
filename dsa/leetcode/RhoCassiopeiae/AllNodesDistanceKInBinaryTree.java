package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 *
 * Traverse the tree starting from the target node. Use DFS to calculate depths and determine nodes
 * at distance K using backtracking approach on parent paths if needed.
 *
 * TC: O(n) SC: O(n)
 * #binary-tree #tree #dfs #bfs #medium
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
    private int dfs(TreeNode root, TreeNode target, boolean ptfound, int ptdepth) {
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

        int ltdepth = dfs(root.left, target, ptfound, ptdepth + (ptfound ? 1 : 0));
        if (ltdepth != -1) {
            tdepth = Math.max(tdepth, 1 + ltdepth);
            if (ltdepth == k) res.add(root.val);
            dfs(root.right, target, true, 1 + ltdepth);
        } else {
            int rtdepth = dfs(root.right, target, ptfound, ptdepth + (ptfound ? 1 : 0));
            if (rtdepth != -1) {
                if (rtdepth == k) res.add(root.val);
                tdepth = Math.max(tdepth, 1 + rtdepth);
                dfs(root.left, target, true, 1 + rtdepth);
            }
        }

        return tdepth;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        this.k = k;
        dfs(root, target, false, 0);
        return res;
    }
}