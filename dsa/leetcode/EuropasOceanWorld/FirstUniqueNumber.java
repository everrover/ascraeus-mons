package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class FirstUnique {
  private Map<Integer, Boolean> uniq = new HashMap<>();
  private Set<Integer> q = new LinkedHashSet<>();

  /**
   * https://leetcode.com/problems/first-unique-number/description/?envType=weekly-question&envId=2025-05-22
   *
   * The idea is to maintain a queue and a hashmap to track unique numbers. The hashmap keeps a record
   * if a number is unique or not. The queue maintains the order of unique elements. When a new
   * number is added, it updates the map status and queue appropriately.

   * TC: O(1) for add and showFirstUnique operations on average
   * SC: O(n) for the storage of hashmap and queue
   * #array #hash-table #design #queue #data-stream #medium
   */

  public FirstUnique(int[] nums) {
    for (int num : nums) {
      this.add(num);
    }
  }

  public int showFirstUnique() {
    if (!q.isEmpty()) return q.iterator().next();
    return -1;
  }

  public void add(int value) {
    if (!uniq.containsKey(value)) {
      uniq.put(value, true);
      q.add(value);
    } else if (uniq.get(value)) {
      uniq.put(value, false);
      q.remove(value);
    }
  }
}