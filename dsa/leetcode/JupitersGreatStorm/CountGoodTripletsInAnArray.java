package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

public class CountGoodTripletsInAnArray {

  /**
   * https://leetcode.com/problems/count-good-triplets-in-an-array/description/?envType=daily-question&envId=2025-04-15
   *
   * A good triplet is a sequence of three numbers (x, y, z) where both nums1 and nums2
   * have indices of x, y, z in increasing order. Use a Fenwick Tree to efficiently count
   * and update the possible good triplets as we iterate through the elements.
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
  }
}