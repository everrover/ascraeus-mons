package dsa.leetcode.VallesMarineris;

import java.util.*;

public class LetterTilePossibilities {
  
  /**
   * https://leetcode.com/problems/letter-tile-possibilities/description/?envType=daily-question&envId=2025-02-17
   *
   * To calculate all the possible sequences, use backtracking with a DFS approach.
   * Consider duplications by incrementing a counter that will help control recursion.
   * 
   * TC: O(n!) SC: O(n)
   * #hash-table #string #backtracking #medium
   */

  public int numTilePossibilities(String tiles) {
    int[] count = new int[26];
    for (char c : tiles.toCharArray()) {
      count[c - 'A']++;
    }
    return dfs(count);
  }

  private int dfs(int[] count) {
    int sum = 0;
    for (int i = 0; i < 26; i++) {
      if (count[i] == 0) continue;
      sum++;
      count[i]--;
      sum += dfs(count);
      count[i]++;
    }
    return sum;
  }
}