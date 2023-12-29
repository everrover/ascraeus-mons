package dsa.leetcode.kuiperBelt;

public class NumberOfSameEndSubstrings {
  /**
   * https://leetcode.com/problems/number-of-same-end-substrings/
   * 
   * Only due to the input character set being small, this solution was made possible via prefix sum arr for each char.
   * 
   * TC: O(n) SC: O(n)
   * 
   * #string #prefix-sum #medium
   */

   public int[] sameEndSubstringCount(String s, int[][] queries) {
    final int n = s.length();
    int [][]prefix = new int[n+1][26];
    int []ans = new int[queries.length];
    for(int i=0; i<n; i++){
      char ch = s.charAt(i);
      prefix[i+1][ch-'a'] = 1;
      for(int j=0; j<26; j++) prefix[i+1][j] += prefix[i][j];
    }
    for(int i=0; i<queries.length; i++){
      int []q = queries[i];
      for(int j=0; j<26; j++){
        int cnt=prefix[q[1]+1][j]-prefix[q[0]][j];
        ans[i] += cnt*(cnt+1)/2;
      }
    }
    return ans;
  }
}
