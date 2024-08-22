package dsa.leetcode.jovianMoonOrbit;

import java.util.HashMap;
import java.util.Map;

/*
 * https://leetcode.com/problems/stickers-to-spell-word/
 *
 * We are given n different types of stickers. Each sticker has a lowercase English word on it.
 * You would like to spell out the given string target by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
 * Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.
 *
 * The solution involves dynamic programming and backtracking. We keep track of the count of each letter in each sticker and try to minimize the number of stickers used by recursively checking valid combinations.
 * 
 * Since, a sticker can be used multiple times, we can use any combination of characters from within the sticker to fetch maximum number of characters from the target string.
 * Basically, dp(target) = min(dp(some substring of target)) + 1
 * If no characters from the sticker are present in the target string, we skip that sticker.
 * 
 * Could've used bitmasks to represent the target string and the stickers for further optimization.
 * Also, found this shortest path solution: https://leetcode.com/problems/stickers-to-spell-word/discuss/962702/Java-4-ms-100-BFS-explanation-and-complexity-analysis
 *
 * TC: O(2^n) SC: O(n)
 * #array #string #dynamic-programming #backtracking #bit-manipulation #hard
 */

public class StickersToSpellWord {
  public int minStickers(String[] stickers, String target) {
    Map<String, Long> map = new HashMap<>();
    int[][] dp = new int[stickers.length][26];
    for (int i = 0; i < stickers.length; i++) {
      for (char c : stickers[i].toCharArray()) dp[i][c - 'a']++;
    }
    map.put("", 0L);
    return (int) dfs(map, dp, target);
  }

  private long dfs(Map<String, Long> map, int[][] dp, String target) {
    if (map.containsKey(target)) return map.get(target);

    long ans = (long) 1e18;
    int[] target1 = new int[26];
    for (char ch : target.toCharArray()) target1[ch - 'a']++;

    for (int i = 0; i < dp.length; i++) {
      if (dp[i][target.charAt(0) - 'a'] <= 0) continue;
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < 26; j++) {
        if (target1[j] - dp[i][j] > 0)
          for (int k = 0; k < (target1[j] - dp[i][j]); k++)
            sb.append((char) ('a' + j));
      }
      long res = dfs(map, dp, sb.toString());
      if (res != -1) ans = Math.min(ans, 1 + res);
    }
    ans = ans == (long) 1e18 ? -1 : ans;
    map.put(target, ans);
    return ans;
  }
  /* // Shortest path solution
  class Solution {
    private boolean empty(int[] freq) {
        for(int f: freq) if(f > 0) return false;
        return true;
    }
    private String toString(int[] freq) {
        StringBuilder sb = new StringBuilder();
        char c = 'a';
        for(int f: freq) {
            while(f-- > 0) sb.append(c);
            c++;
        }
        return sb.toString();
    }
    public int minStickers(String[] stickers, String target) {
        // Optimization 1: Maintain frequency only for characters present in target
        int[] targetNaiveCount = new int[26];
        for(char c: target.toCharArray()) targetNaiveCount[c - 'a']++;
        int[] index = new int[26];
        int N = 0;  // no of distinct characters in target
        for(int i = 0; i < 26; i++) index[i] = targetNaiveCount[i] > 0 ? N++ : -1;
        int[] targetCount = new int[N];
        int t = 0;
        for(int c: targetNaiveCount) if(c > 0) {
            targetCount[t++] = c;
        }
        int[][] stickersCount = new int[stickers.length][N];
        for(int i = 0; i < stickers.length; i++) {
            for(char c: stickers[i].toCharArray()) {
                int j = index[c - 'a'];
                if(j >= 0) stickersCount[i][j]++;
            }
        }
        // Optimization 2: Remove stickers dominated by some other sticker
        int start = 0;
        for(int i = 0; i < stickers.length; i++) {
            for(int j = start; j < stickers.length; j++) if(j != i) {
                int k = 0;
                while(k < N && stickersCount[i][k] <= stickersCount[j][k]) k++;
                if(k == N) {
                    int[] tmp = stickersCount[i];
                    stickersCount[i] = stickersCount[start];
                    stickersCount[start++] = tmp;
                    break;
                }
            }
        }
        // Perform BFS with target as source and an empty string as destination
        Queue<int[]> Q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Q.add(targetCount);
        int steps = 0;
        while(!Q.isEmpty()) {
            steps++;
            int size = Q.size();
            while(size-- > 0) {
                int[] freq = Q.poll();
                String cur = toString(freq);
                if(visited.add(cur)) {
                    // Optimization 3: Only use stickers that are capable of removing first character from current string
                    int first = cur.charAt(0) - 'a';
                    for(int i = start; i < stickers.length; i++) if(stickersCount[i][first] != 0) {
                        int[] next = freq.clone();
                        for(int j = 0; j < N; j++) next[j] = Math.max(next[j] - stickersCount[i][j], 0);
                        if(empty(next)) return steps;
                        Q.add(next);
                    }
                }
            }
        }
        return -1;
    }
}
   */
}