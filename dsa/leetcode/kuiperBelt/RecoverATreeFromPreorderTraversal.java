package dsa.leetcode.KuiperBelt;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
 * Using a depth-first search (DFS) approach, this code recovers a binary tree from its preorder traversal output.
 * Each node is preceded by dashes equal to its depth, and we utilize this pattern to reconstruct the tree accordingly.
 * 
 * The code first parses the input string to extract the depth and value of each node.
 * It then recursively constructs the tree by traversing the list of nodes.
 * If the next node has a depth greater than the current node, it is added as the left child and then the right child
 * If not, the current node is returned since no further children are present.
 * 
 * 
 * TC: O(n), where n is the length of the traversal string.
 * SC: O(d), where d is the max depth of the tree.
 * #tree #dfs #binary-tree #hard
 */

class RecoverATreeFromPreorderTraversal {
    private static class TreeNode {
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
  private static class T {
    public int num, depth, idx;
    public T(int num, int dep, int idx){
      this.num = num;
      this.depth = dep;
      this.idx = idx;
    }
    
    public String toString(){
      return "{"+num+":"+depth+"}";
    }
  }
  private T next(char []chs, int idx){
    int pow=0, cnt=0, i=idx, val=0;
    while(i<chs.length){
      if(chs[i] != '-') break;
      cnt++;
      i++;
    }
    while(i<chs.length){
      if(chs[i] == '-') break;
      val=(10*val)+(chs[i]-'0');
      pow++;i++;
    }
    return new T(val, cnt, i==chs.length?-1:i);
  }
  public TreeNode recoverFromPreorder(String traversal) {
    TreeNode root = null;
    char []chs = traversal.toCharArray();
    int idx = 0;
    T t = next(chs, 0);
    List<T> al = new ArrayList<>(1000);
    while(t!=null){
      al.add(t);
      if(t.idx == -1) break;
      t = next(chs, t.idx);
    }
    return rec(al, new int[]{0});
  }
  private TreeNode rec(List<T> al, int []ids){
    if(ids[0]>=al.size()) return null;
    int idx = ids[0];
    TreeNode root = new TreeNode(al.get(idx).num);
    if(ids[0]<(al.size()-1) && al.get(ids[0]+1).depth>al.get(idx).depth) {
      ids[0]++;
      root.left = rec(al, ids);
    }
    if(ids[0]<(al.size()-1) && al.get(ids[0]+1).depth>al.get(idx).depth) {
      ids[0]++;
      root.right = rec(al, ids);
    }
    return root;
  }
}