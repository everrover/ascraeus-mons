package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

public class CountGoodTripletsInAnArray {

  /**
   * https://leetcode.com/problems/count-good-triplets-in-an-array/description/?envType=daily-question&envId=2025-04-15
   *
   * Needed help here!
   * For each element taken, we check to it's left and right elements which form a valid triplet.
   * 
   * For each number in nums2, we find it's position in nums1 and store it in reversedIndexMapping.
   * 
   * For each value in [0, n), we find the position in nums1 w.r.t. nums2 and update the Fenwick Tree.
   * Using the fenwick tree for this value, we find the number of elements to the left
   * and the number of elements to the right.
   *
   * TC: O(n log n)  SC: O(n)
   *
   * #array #binary-search #divide-and-conquer #binary-indexed-tree #segment-tree #hard
   */

   private static class FT {
    private int[] tree;

    public FT(int size) {
      tree = new int[size + 1];
    }

    public void update(int index, int delta) {
      index++;
      while (index < tree.length) {
        tree[index] += delta;
        index += index & -index;
      }
    }

    public int query(int index) {
      index++;
      int res = 0;
      while (index > 0) {
        res += tree[index];
        index -= index & -index;
      }
      return res;
    }
  }
  public long goodTriplets(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int[] pos2 = new int[n], reversedIndexMapping = new int[n];
    for (int i = 0; i < n; i++) {
      pos2[nums2[i]] = i;
    }
    for (int i = 0; i < n; i++) {
      reversedIndexMapping[pos2[nums1[i]]] = i;
    }
    FT tree = new FT(n);
    long res = 0;
    for (int value = 0; value < n; value++) {
      int pos = reversedIndexMapping[value];
      int left = tree.query(pos);
      tree.update(pos, 1);
      // (right elements to `pos` in n1) - (total possible left elements in n2 - elements to left in n2 intersecting with elements in left of n1)
      int right = (n - 1 - pos) - (value - left);
      res += (long) left * right;
    }
    return res;
  }
}