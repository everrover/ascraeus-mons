package dsa.leetcode.mithrim_montez;

import java.util.*;

public class ConstructBinaryTreeFromString {
  /**
   * https://leetcode.com/problems/construct-binary-tree-from-string/?envType=company&envId=facebook&favoriteSlug=facebook-all
   * 
   * To construct a binary tree from a string, traverse the string with a stack to
   * track nodes. Parse integers, manage the parenthesis to track left/right
   * child positions, and build the tree structure accordingly.
   *
   * TC: O(n) SC: O(n)
   * #string #stack #tree #dfs #binary-tree #medium
   */

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
   */
  public TreeNode str2tree(String s) {
    Stack<TreeNode> st = new Stack<>();
    TreeNode parent = null, curr = null;
    int si = 0, sign = 1;
    while(si<s.length()){
      if(s.charAt(si) == ')'){
        curr = st.pop();
        parent = st.peek();
        if(parent.left == null){
          parent.left = curr;
        }else{
          parent.right = curr;
        }
        si++;
      }else if(s.charAt(si) == '-'){
        sign=-1;
        si++;
      }else if(s.charAt(si) == '('){
        si++;
      }else{
        int num = 0;
        while(si<s.length() && s.charAt(si)>='0' && s.charAt(si)<='9'){
          num = num*10+s.charAt(si)-'0';
          si++;
        }
        num *= sign;
        sign = 1;
        st.push(new TreeNode(num));
      }
    }
    if(st.isEmpty()) return null;
    return st.pop();
  }
}

/**

// st = ( 4 ( 6)
4
|
2
| \
3 1

(4(2(3)(1))(6(5)))
 */