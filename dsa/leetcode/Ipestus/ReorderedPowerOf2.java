package dsa.leetcode.Ipestus;

import java.util.*;

public class ReorderedPowerOf2 {
  /**
   * https://leetcode.com/problems/reordered-power-of-2/description/
   *
   * To determine if the number can be reordered to form a power of two:
   * - Count the frequency of each digit in the given number.
   * - Compare this frequency against each power of two within the constraints.
   * - If any reordering matches the digits of a power of two, return true.
   *
   * TC: O(1) as maximum of 30 digit permutations checked.
   * #hash-table #math #sorting #medium
   */
  
  public boolean reorderedPowerOf2(int n) {
    Set<Integer> twos = new TreeSet<>();
    for (int i = 1; i < 1e9; i *= 2) twos.add(i);

    int[] ncnt = countdigs(n);
    for (int num : twos) {
      int[] numcnt = countdigs(num);
      boolean res = true;
      for (int i = 0; i < 10; i++) {
        if (numcnt[i] != ncnt[i]) {
          res = false;
          break;
        }
      }
      if (res) return true;
    }
    return false;
  }

  private int[] countdigs(int n) {
    int[] cnt = new int[10];
    while (n > 0) {
      cnt[n % 10]++;
      n /= 10;
    }
    return cnt;
  }
}