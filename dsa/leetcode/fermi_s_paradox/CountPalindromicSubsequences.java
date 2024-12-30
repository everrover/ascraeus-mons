package dsa.leetcode.fermi_s_paradox;

public class CountPalindromicSubsequences {

  /**
   * https://leetcode.com/problems/count-palindromic-subsequences/?envType=company&envId=goldman-sachs&favoriteSlug=goldman-sachs-all
   * 
   * This solution calculates the number of palindromic subsequences of length 5 in a given string.
   * It uses dynamic programming to track occurrences of two digit pairs as prefixes and suffixes,
   * allowing for combination of a middle digit to form palindromic subsequences.
   * 
   * This problem involved a ton of precision as per the constraints.
   * 
   * First, 
   * 
   * TC: O(n) SC: O(1)
   * #string #dynamic-programming #hard
   */

  public int countPalindromes(String s) {
      long res = 0; 
      int[] found = new int[10]; 
      long[][] pre = new long[100][s.length()]; 
      long[][] suf = new long[100][s.length()];
      
      // Initial calculation of prefix occurrences
      for(int i = 0; i < s.length(); i++) {
          int a = s.charAt(i) - '0';
          for(int k = 0; k <= 9; k++)
              pre[k * 10 + a][i] += found[k];
          found[a]++;
          if(i > 0)
              for(int j = 0; j < 100; j++)
                  pre[j][i] += pre[j][i - 1];
      }

      // Reset found for suffix calculation
      found = new int[10];

      // Calculate suffix occurrences and combine for result
      for (int i = s.length() - 1; i >= 0; i--) {
          int a = s.charAt(i) - '0';
          for (int k = 0; k <= 9; k++)
              suf[a * 10 + k][i] += found[k];
          found[a]++;
          if (i < s.length() - 1)
              for (int j = 0; j < 100; j++)
                  suf[j][i] += suf[j][i + 1];

          // Calculate result using pre and suf arrays
          if (i > 1 && i < s.length() - 2) {
              for (int j = 0; j < 100; j++) {
                  res = (res + pre[j][i - 1] * suf[j][i + 1]) % MOD;
              }
          }
      }

      return (int) res;
  }

  private static final int MOD = (int) 1e9 + 7;
}