package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class NumberOfWaysToFormTargetStringGivenDictionary {
  /**
   * https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/?envType=daily-question&envId=2024-12-29
   *
   * Use frequency array to track available characters. Once a character is used, it's position can't
   * be used again. So, we either skip or use the current character for the target string.
   * 
   * TC: O(m*t), m is the word length[ALL WORDS ARE SAME], and t is the target length.
   * SC: O(m*t + m*m)
   * #dynamic-programming #string #hard
   */
  
  private static int MOD = (int)1e9+7;
  private int dfs(int i, int j, final int [][]words, final String target, final int [][]dp){
    if(j == target.length()) return 1;
    else if(i == words.length) return 0;
    else if(dp[i][j] != -1) return dp[i][j];

    // don't include the current character for the target string
    // since i+1 if used won't allow use of i again we don't iterate for all words
    long cnt = dfs(i+1, j, words, target, dp)%MOD;

    // include the current character for the target string
    if(words[i][target.charAt(j)-'a'] > 0) cnt = (cnt + 1L * words[i][target.charAt(j)-'a'] * dfs(i+1, j+1, words, target, dp))%MOD;

    return dp[i][j] = (int)cnt;
  }
  public int numWays(String[] words, String target) {
    final int M = words[0].length();
    int [][]wa = new int[M][26];
    for(String word: words){
      for(int i=0; i<M; i++){
        wa[i][word.charAt(i)-'a']++;
      }
    }
    int [][]dp = new int[M][target.length()];
    for(int []dd: dp) Arrays.fill(dd, -1);
    return dfs(0,0,wa,target,dp);
  }
}