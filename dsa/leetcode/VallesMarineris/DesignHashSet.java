package dsa.leetcode.VallesMarineris;

public class MyHashSet {
  private int[] tb;

  /**
   * https://leetcode.com/problems/design-hashset/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
   *
   * The implemented hash set uses an array where each index corresponds to a potential key, with simple operations for add, remove, and check existence.
   *
   * TC: O(1) for add, remove, and contains
   * SC: O(1) since the array is fixed in size and operations do not depend on the number of stored elements
   * #array #hash-table #design #easy
   */

  public MyHashSet() {
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