package dsa.leetcode.Ipestus;

import java.util.*;

public class ValidateBinaryTreeNodes {

  /**
   * https://leetcode.com/problems/validate-binary-tree-nodes/
   *
   * To determine if the nodes form a valid binary tree, ensure:
   * 1. There is exactly one node (root) with no parent.
   * 2. Every node, except the root, has at most one parent.
   * 3. All nodes are connected to the root.
   * Using arrays to identify parent nodes and check conditions efficiently.
   *
   * TC: O(n) SC: O(n)
   * #tree #depth-first-search #breadth-first-search #union-find #graph #binary-tree #medium
   */

  public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
    int []parent = new int[n];
    Arrays.fill(parent, -1);

    for (int i = 0; i < leftChild.length; i++) {
      if (leftChild[i] != -1) {
        if (parent[leftChild[i]] != -1) return false;
        else parent[leftChild[i]] = i;
      }
    }

    for (int i = 0; i < rightChild.length; i++) {
      if (rightChild[i] != -1) {
        if (parent[rightChild[i]] != -1) return false;
        else parent[rightChild[i]] = i;
      }
    }

    /**
     * Find the root node by counting nodes without parents.
     */

    int rootCount = 0;
    for (int i = 0; i < n; i++) {
      if (parent[i] == -1) rootCount++;
    }

    return rootCount == 1;
  }
}