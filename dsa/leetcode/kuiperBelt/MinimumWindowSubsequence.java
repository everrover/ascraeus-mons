package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

// URL: https://leetcode.com/problems/minimum-window-subsequence/
// Time Complexity: Not provided
// Space Complexity: Not provided
// Tags: #dynamic-programming #sliding-window #string #hard

class Solution {
  public String minWindow(String s1, String s2) {
    int [][]dp = new int[s1.length()][s2.length()];
    for(int []d: dp) Arrays.fill(d, -1);
    int []minLenAStart = new int[]{Integer.MAX_VALUE, -1};
    dfs(0,0,s1.toCharArray(),s2.toCharArray(),dp, minLenAStart);
    if(minLenAStart[1]==-1) return "";
    return s1.substring(minLenAStart[1],minLenAStart[1]+minLenAStart[0]);
  }

  private int dfs(int i, int j, char []s1, char []s2, int [][]dp, int []minLenAStart){
    if(j == s2.length) return i;
    else if(i==s1.length) return Integer.MAXITY;
    int matchedIdx = Integer.MAXITY;
    if(s1[i] == s2[j]){
      matchedIdx = dfs(i+1,j+1,s1,s2,dp,minLenAStart);
    }
    int skippedIdx = dfs(i+1,j,s1,s2,dp,minLenAStart);
    int res = Math.min(skippedIdx, matchedIdx);
    if(j == 0 && res != Integer.MAXITY && (minLenAStart[1]==-1 || res-i <= minLenAStart[0])){
      minLenAStart[0] = res-i;
      minLenAStart[1] = i;
    }
    return dp[i][j]=res;
  }
}

/*
Recursive DFS with memoization to find minimum window substring length.
*/