package dsa.leetcode.mithrim_montez;

class MaximumDifferenceBetweenEvenAndOddFrequency {

  /**
   * https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/description/
   *
   * Use a frequency map to identify the maximum odd and minimum even frequencies. Then, calculate their difference.
   *
   * TC: O(n) SC: O(1)
   * #frequency-map #greedy #easy
   */

  public int maxDifference(String s) {
    int[] cnts = new int[26];
    for (char ch : s.toCharArray()) {
      cnts[ch - 'a']++;
    }
    int evenMax = -1, evenMin = 101, oddMax = -1, oddMin = 101, res = -2100;
    for (int cnt : cnts) {
      if (cnt == 0) continue;
      if (cnt % 2 == 0) {
        evenMax = Math.max(evenMax, cnt);
        evenMin = Math.min(evenMin, cnt);
      } else {
        oddMin = Math.min(oddMin, cnt);
        oddMax = Math.max(oddMax, cnt);
      }
    }

    // Check and update maximum difference calculated
    if (oddMax != -1 && evenMin != 101) res = Math.max(oddMax - evenMin, res);
    if (evenMax != -1 && oddMin != 101) res = Math.max(oddMin - evenMax, res);
    return res;
  }
}