package dsa.leetcode.KuiperBelt;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * This code provides a custom method to serialize and deserialize a binary tree.
 * Serialization is converting a tree into a string format with just DFS(inorder-traversal), 
 * and deserialization is rebuilding the tree from that string using same DFS order.
 * 
 * Earlier thought of using in-order and pre-order traversal to serialize,
 * and then using pre-order to find the root and in-order to find left and right subtrees by splitting the string(to be deserialized).
 * 
 * Complexity: O(n) for both serialization and deserialization.
 * 
 * #tree #dfs #design #hard #think-simple-stupid #think-simple-then-complex
 */
public class SerializeAndDeserializeBinaryTree {

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  private String inOrder(TreeNode root){
    if(root == null) return "X,";
    String l = inOrder(root.left);
    String r = inOrder(root.right);
    StringBuilder sb = new StringBuilder();
    sb.append(root.val).append(',');
    if(l != null) sb.append(l);
    if(r != null) sb.append(r);
    return sb.toString();
  }

  public String serialize(TreeNode root) {
    String pre = inOrder(root);
    return pre;
  }

  public TreeNode deserialize(String data) {
    String []split = data.split(",");
    List<String> ll = new LinkedList<>(Arrays.asList(split));
    return recDes(ll);
  }

  private TreeNode recDes(List<String> ll){
    if(ll.get(0).equals("X")){
      ll.remove(0);
      return null;
    }
    TreeNode root = new TreeNode(Integer.parseInt(ll.get(0)));
    ll.remove(0);
    root.left = recDes(ll);
    root.right = recDes(ll);
    return root;
  }
}