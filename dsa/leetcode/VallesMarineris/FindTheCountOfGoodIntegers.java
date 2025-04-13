package dsa.leetcode.VallesMarineris;

import java.util.*;

public class FindTheCountOfGoodIntegers {

  /**
   * https://leetcode.com/problems/find-the-count-of-good-integers/description/?envType=daily-question&envId=2025-04-12
   *
   * Generates k-palindromic numbers by creating possible palindrome combinations and
   * checking divisibility by k. Calculates permutations without leading zeros.
   *
   * TC: O(9^(n/2) * n log n) SC: O(9^(n/2))
   * #hash-table #math #combinatorics #hard
   */

  // we'll compute the factorials of 0-10 using a loop in a contest
  // for(int i = 1; i <= n; i++) { fact[i] = fact[i-1] * i; }
  private long[] fact = new long[]{1,1,2,6,24,120,720,5040,40320,362880, 3628800};

  public long countGoodIntegers(int n, int k) {
    Set<String> dict = new TreeSet<>();
    int b = (int)Math.pow(10, (n - 1) / 2);
    int isOddBal = n & 1;
    for(int i = b; i < b * 10; i++) {
      String s = Integer.toString(i);
      s += new StringBuilder(s).reverse().substring(isOddBal);
      long palindromicInteger = Long.parseLong(s);
      if (palindromicInteger % k == 0) {
        char[] chs = s.toCharArray();
        Arrays.sort(chs);
        dict.add(new String(chs));
      }
    }
    long ans = 0;
    for (String s : dict) {
      int[] cnt = new int[10];
      for (char c : s.toCharArray()) {
        cnt[c - '0']++;
      }
      /* Calculate permutations and combinations */
      long tot = (n - cnt[0]) * fact[n - 1];
      for (int x : cnt) {
        tot /= fact[x];
      }
      ans += tot;
    }
    return ans;
  }

}