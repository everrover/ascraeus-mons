package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class FruitIntoBaskets {

  /**
   * https://leetcode.com/problems/fruit-into-baskets/submissions/
   *
   * You only have two baskets, and each basket can only hold a single type of fruit. Starting from any tree, pick fruits until you cannot fit them in the two baskets. Keep track of fruit counts with a hashmap and use the sliding window technique to find the maximum number of fruits you can pick.
   *
   * TC: O(n) SC: O(1)
   * #array #hash-table #sliding-window #medium
   */

  public int totalFruit(int[] fruits) {
    Map<Integer, Integer> cntMap = new HashMap<>();
    int i = 0, j = 0, res = 0;
    for (; j < fruits.length; j++) {
      cntMap.put(fruits[j], cntMap.getOrDefault(fruits[j], 0) + 1);
      while (cntMap.size() > 2) {
        cntMap.put(fruits[i], cntMap.get(fruits[i]) - 1);
        if (cntMap.get(fruits[i]) == 0) cntMap.remove(fruits[i]);
        i++;
      }
      res = Math.max(j - i + 1, res);
    }
    return res;
  }
}