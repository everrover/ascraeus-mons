package dsa.leetcode.kuiperBelt;

import java.util.LinkedList;
import java.util.List;

public class DistributeElementsIntoTwoArraysII {

  /*
   * https://leetcode.com/problems/distribute-elements-into-two-arrays-ii/
   * 
   * Utilizes AVL tree(with size, not ht) for determining the number of elements greater than a given value upon insertion. 
   * If key<node.key => key<node.key<=node.right.key(s), hence all included, but can't be sure for left subtree. So we recurse for it.
   * If key>=node.key => key>=node.key>=node.left.key(s), hence all excluded, but right subtree might hv a few valid keys. So we recurse for it.
   * The elements are distributed based on specific conditions into two different arrays. Inorder traversal could've been used for space optimization.
   * 
   * Fenwick tree can also be used to solve the problem. Though some augmentation is required to solve 
   * the problem. Since, memory is a constraint if it were used(Got MLE). Some ranking based approach maybe.
   *
   * TC: O(n log n) due to AVL tree insertions. SC: O(n) for storing elements.
   * #tree #binary-search-tree #array #hard #avl-tree #fenwick-tree
   */

   private static class Node {
    public int key, size;
    public Node left, right;

    Node(int d) {
      key = d;
      size = 1;
    }
  }

  private static class AVLTree {
    public Node root;
    
    private void updateSize(Node curr){
      if(curr == null) return;
      curr.size = (curr.left == null? 0:curr.left.size)+(curr.right == null? 0:curr.right.size)+1;
    }

    private Node rightRotate(Node y) {
      Node x = y.left;
      y.left = x.right;
      x.right = y;
      updateSize(y);
      updateSize(x);
      return x;
    }

    private Node leftRotate(Node x) {
      Node y = x.right;
      x.right = y.left;
      y.left = x;
      updateSize(x);
      updateSize(y);
      return y;
    }

    int getBalance(Node N) {
      if (N == null) return 0;
      return (N.left==null?0:N.left.size) - (N.right==null?0:N.right.size);
    }

    Node insert(Node node, int key) {
      if (node == null) return new Node(key);
      if (key < node.key) node.left = insert(node.left, key);
      else node.right = insert(node.right, key);

      updateSize(node);

      int balance = getBalance(node);

      if (balance > 1)
          return rightRotate(node);
      else if (balance < -1)
          return leftRotate(node);
      return node;
    }
    
    public int countGreater(Node node, int x){
      if (node == null) return 0;
      if(x == 32)
        x=32;
      // System.out.println(":::"+node.key+":"+x);
      if (x < node.key) return (node.right==null?0:node.right.size)+1+countGreater(node.left, x);
      else return countGreater(node.right, x);
    }
    
    public void insert(int k){
      root = insert(root, k);
    }
  }
  public int[] resultArray(int[] nums) {
    int []res = new int[nums.length];
    AVLTree ft1 = new AVLTree();
    AVLTree ft2 = new AVLTree();
    List<Integer> arr1 = new LinkedList<>();
    List<Integer> arr2 = new LinkedList<>();
    ft1.insert(nums[0]);arr1.add(nums[0]);
    ft2.insert(nums[1]);arr2.add(nums[1]);
    for(int i=2; i<nums.length; i++){
      int q1 = ft1.countGreater(ft1.root,nums[i]);
      int q2 = ft2.countGreater(ft2.root,nums[i]);
      // ft1.inorderTraversal(ft1.root);
      // System.out.println(":::"+nums[i]+":"+q1);
      // ft2.inorderTraversal(ft2.root);
      // System.out.println(":::"+nums[i]+":"+q2);
      if(q1>q2){
        ft1.insert(nums[i]);
        arr1.add(nums[i]);
      }else if(q1<q2){
        ft2.insert(nums[i]);
        arr2.add(nums[i]);
      }else if(arr1.size()>arr2.size()){
        ft2.insert(nums[i]);
        arr2.add(nums[i]);
      }else{
        ft1.insert(nums[i]);
        arr1.add(nums[i]);
      }
    }
    arr1.addAll(arr2); int i=0;
    for(int a: arr1) res[i++] = a;
    return res;
  }

  // // Alternative solution using Fenwick Tree

  // public class FenwickTree {
  //   private int[] tree;

  //   public FenwickTree(int n) {
  //     this.tree = new int[n + 1];
  //   }

  //   public void update(int i, int delta) {
  //     while (i < tree.length) {
  //       tree[i] += delta;
  //       i += i & -i;
  //     }
  //   }
    
  //   public int sumrange(int a, int b){
  //     // for(int t: tree) System.out.print(t+":");
  //     // System.out.println();
  //     return query(b) - query(a-1);
  //   }

  //   public int query(int i) {
  //     int sum = 0;
  //     while (i > 0) {
  //       sum += tree[i];
  //       i -= i & -i;
  //     }
  //     return sum;
  //   }
  // }
  // public int[] resultArray(int[] nums) {
  //   int []res = new int[nums.length];
  //   int max = 0;
  //   for(int x: nums) max = Math.max(max, x);
  //   FenwickTree ft1 = new FenwickTree(max+1);
  //   FenwickTree ft2 = new FenwickTree(max+1);
  //   List<Integer> arr1 = new LinkedList<>();
  //   List<Integer> arr2 = new LinkedList<>();
  //   ft1.update(nums[0],1);arr1.add(nums[0]);
  //   ft2.update(nums[1],1);arr2.add(nums[1]);
  //   for(int i=2; i<nums.length; i++){
  //     int q1 = ft1.sumrange(nums[i]+1,max);
  //     int q2 = ft2.sumrange(nums[i]+1,max);
  //     if(q1>q2){
  //       ft1.update(nums[i], 1);
  //       arr1.add(nums[i]);
  //     }else if(q1<q2){
  //       ft2.update(nums[i], 1);
  //       arr2.add(nums[i]);
  //     }else if(arr1.size()>arr2.size()){
  //       ft2.update(nums[i], 1);
  //       arr2.add(nums[i]);
  //     }else{
  //       ft1.update(nums[i], 1);
  //       arr1.add(nums[i]);
  //     }
  //   }
  //   arr1.addAll(arr2); int i=0;
  //   for(int a: arr1) res[i++] = a;
  //   return res;
  // }
}