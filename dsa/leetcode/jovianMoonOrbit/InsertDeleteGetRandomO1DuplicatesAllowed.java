package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class RandomizedCollection {
  /**
   * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
   * 
   * This solution uses a List to store elements and a Map to track indices of each element in the List. 
   * For insertion, the element is added to the list and the index is recorded in the map. 
   * For removal, the element's index is removed from the map, and the last element in the list is moved 
   * to the position of the element that is to be removed. The method getRandom simply returns a random 
   * element from the list.
   * 
   * TC: O(1) on average for each operation
   * SC: O(n)
   * 
   * #array #hash-table #design #randomized #hard
   */
  
  Map<Integer, Set<Integer>> set;
  List<Integer> list;
  Random rand = new Random();

  public RandomizedCollection() {
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