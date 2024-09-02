package dsa.leetcode.jovianMoonOrbit;

import java.util.Random;

public class RandomPickWithWeight {
  /**
   * https://leetcode.com/problems/random-pick-with-weight/
   *
   * Compute prefix sums array and use binary search to find the index.
   *
   * TC: O(log n) for pickIndex()
   * SC: O(n) for storing prefix sums
   * #array #math #binary-search #prefix-sum #randomized #medium
   */
  int[] prefix;
  Random rand;
  int end;

  public RandomPickWithWeight(int[] w) {
    this.prefix = new int[w.length + 1];
    prefix[0] = w[0];
    for (int i = 1; i < w.length; i++) {
      prefix[i] = prefix[i - 1] + w[i];
    }
    prefix[w.length] = Integer.MAX_VALUE; // To avoid overflow
    this.end = prefix[w.length - 1];
    this.rand = new Random();
  }

  private int genRandom() {
    return rand.nextInt(end) + 1;
  }

  public int pickIndex() {
    int ran = genRandom();
    int l = 0, r = prefix.length - 1, m = 0;
    while (l < r) {
      m = (r + l) / 2;
      if (prefix[m] < ran) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    return l;
  }
}

/**
 * Your RandomPickWithWeight object will be instantiated and called as such:
 * RandomPickWithWeight obj = new RandomPickWithWeight(w);
 * int param_1 = obj.pickIndex();
 */