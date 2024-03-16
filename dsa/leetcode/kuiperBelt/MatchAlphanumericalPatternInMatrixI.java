package dsa.leetcode.kuiperBelt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  /**
   * Applied simple brute force(no backtracking here) approach to solve the problem.
   * 
   * Time complexity: O(m*n*pattern.length*pattern[0].length())
   * Space complexity: O(1)
   * 
   * #hashmap #set #brute-force #matrix #medium
   */

  // private static Map<Character, Integer> map = new HashMap<>();
  // private static Set<Integer> set = new HashSet<>();
  public int[] findPattern(int[][] board, String[] pattern) {
    final int m = board.length-pattern.length+1, n = board[0].length-pattern[0].length()+1;
    int []res = new int[]{-1, -1};
    for(int i=0; i<m; i++) for(int j=0; j<n; j++)
      if(matches(board, pattern, i, j)){
        res[0] = i; res[1] = j;
        return res;
      }
    return res;
  }
  
  private boolean matches(int b[][], String p[], int r, int c){
    Map<Character, Integer> map = new HashMap<>();
    Set<Integer> set = new HashSet<>();
    map.clear();
    set.clear();
    for(int i=0; i<p.length; i++){
      for(int j=0; j<p[0].length(); j++){
        char ch = p[i].charAt(j);
        if(Character.isDigit(ch)){
          if(ch-'0' == b[r+i][c+j]){
            continue;
          }else{
            return false;
          }
        }
        if(map.get(ch) == null){
          if(set.contains(b[r+i][c+j])) return false;
          map.put(ch, b[r+i][c+j]);
          set.add(b[r+i][c+j]);
        }
        if(map.get(ch) != b[r+i][c+j]){
          return false;
        }
      }
    }
    return true;
  }
}