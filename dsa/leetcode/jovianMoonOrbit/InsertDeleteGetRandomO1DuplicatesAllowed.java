package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class InsertDeleteGetRandomO1DuplicatesAllowed {
  /**
   * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
   * 
   * ~ to this (problem)[./InsertDeleteGetRandomO1.java]
   * 
   * **A list along with random number generator actually mimics a probability distribution**, where the 
   * probability of selecting an element is dependent on the size and number of occurrences of the 
   * element in the list. And not only on latter.
   * 
   * - Earlier I tried using a count map to store the count of each element, but that doesn't work because
   * probability of selecting an element is dependent on the number of unique elements in the list. And not 
   * at all on the number of occurrences of the element at all.
   *  
   * TC: O(1) on average for each operation
   * SC: O(n)
   * 
   * #array #hash-table #design #randomized #hard
   */
  
  Map<Integer, Set<Integer>> set;
  List<Integer> list;
  Random rand = new Random();

  public InsertDeleteGetRandomO1DuplicatesAllowed() {
    this.set = new HashMap<>();
    this.list = new ArrayList<>();
  }

  public boolean insert(int val) {
    set.putIfAbsent(val, new LinkedHashSet<Integer>());
    set.get(val).add(list.size());
    list.add(val);
    return set.get(val).size() == 1;
  }

  public boolean remove(int val) {
    if (!set.containsKey(val) || set.get(val).size() == 0) return false;
    int idxtoremove = set.get(val).iterator().next(); // get first inserted multi-element
    set.get(val).remove(idxtoremove);
    // move last element to current `idxtoremove` position
    int last = list.get(list.size() - 1);
    list.set(idxtoremove, last);
    set.get(last).add(idxtoremove);
    set.get(last).remove(list.size() - 1);
    list.remove(list.size() - 1);
    return true;
  }

  public int getRandom() {
    return list.get(rand.nextInt(list.size()));
  }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */