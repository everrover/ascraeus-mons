package dsa.leetcode.JovianMoonOrbit;

import java.util.*;

public class InsertDeleteGetRandomO1 {
  
  /**
   * https://leetcode.com/problems/insert-delete-getrandom-o1/
   * 
   * Implemented using a combination of a hash map and an array list.
   * Supports operations in average O(1) time complexity.
   * 
   * ~ algos used in popular statistical algorithms like Markov chain 
   * Monte Carlo and Metropolis–Hastings algorithm.
   * 
   * TC: O(1) on average SC: O(n)
   * #hash-map #array-list #random #medium
   */

  Map<Integer, Integer> set;
  List<Integer> list;
  Random rand = new Random();

  public InsertDeleteGetRandomO1() {
    this.set = new HashMap<>();
    this.list = new ArrayList<>();
  }

  public boolean insert(int val) {
    if(set.containsKey(val)) return false;
    set.put(val, list.size());
    list.add(val);
    return true;
  }

  public boolean remove(int val) {
    if(!set.containsKey(val)) return false;
    int last = list.get(list.size()-1);
    int idx = set.get(val);
    list.set(idx, last);
    set.put(last, idx);
    set.remove(val);
    list.remove(list.size()-1);
    return true;
  }

  public int getRandom() {
    return list.get((int)(list.size()*Math.random()));
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */