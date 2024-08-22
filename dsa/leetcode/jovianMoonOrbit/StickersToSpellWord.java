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
}