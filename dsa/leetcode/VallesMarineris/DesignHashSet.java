package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class DesignHashSet {
  private int[] tb;

  /**
   * https://leetcode.com/problems/design-hashset/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
   *
   * The implemented hash set uses an array where each index corresponds to a potential key, with simple operations for add, remove, and check existence.
   * 
   * Classic textbook problem. 
   * 
   * The original hash collision resolution techniques
   * - Using List of LinkedLists and hashes to index the list. In java: List<LinkedList<Integer>>
   * - Using a balanced binary search tree to store the elements at hash index. In java: List<TreeSet<Integer>>
   * - Using a chained list. In java: List<List<Integer>>
   * 
   * Add and remove elements in case of collision needs to seek the actual element before the ops
   *
   * TC: O(1) for add, remove, and contains
   * SC: O(1) since the array is fixed in size and operations do not depend on the number of stored elements
   * #array #hash-table #design #easy
   */

  public DesignHashSet() {
    tb = new int[1000001];
    Arrays.fill(tb, -1); // Initialize array with -1 to represent empty slots
  }

  public void add(int key) {
    tb[key] = 1; // Mark the key as present in the set
  }

  public void remove(int key) {
    tb[key] = -1; // Mark the key as not present in the set
  }

  public boolean contains(int key) {
    return tb[key] == 1; // Check if the key is present in the set
  }
}