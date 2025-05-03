package dsa.leetcode.KuiperBelt;

import dsa.leetcode.internals.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/
 *
 * The approach involves BFS/DFS to traverse the tree while keeping track of columns.
 * We use a depth index for sorting, ensuring their order from left to right and top to bottom is maintained.
 * 
 * TC: O(NlogN) due to sorting, SC: O(N) for storing nodes.
 *
 * #hash-table #tree #depth-first-search #breadth-first-search #sorting #binary-tree #medium
 */
class BinaryTreeVerticalOrderTraversal {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    if(root == null) return new LinkedList<>();
    List<List<int[]>> ans = new ArrayList<>(201);
    List<List<Integer>> aans = new ArrayList<>();
    for(int a=0; a<201; a++) ans.add(new ArrayList<>());
    bfs(root, ans, 0, 0);
    for(List<int[]> a: ans){
      if(a.isEmpty()) continue;
      Collections.sort(a, (c,d)->c[1]-d[1]);
      List<Integer> ansl = new LinkedList<>();
      for(int[] b: a) ansl.add(b[0]);
      aans.add(ansl);
    }
    return aans;
  }

  private void bfs(TreeNode root, List<List<int[]>> ans, int idx, int depth){
    if(root.left != null) bfs(root.left, ans, idx-1, depth+1);
    ans.get(idx+100).add(new int[]{root.val, depth});
    if(root.right != null) bfs(root.right, ans, idx+1, depth+1);
  }
}