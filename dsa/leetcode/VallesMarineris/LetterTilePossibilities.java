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

   private final int[] fact = {1,1,2,6,24,120,720,5040};
   public int numTilePossibilities(String tiles) {
     Map<Character, Integer> map = new HashMap<>();
     for(char ch: tiles.toCharArray()){
       map.put(ch, map.getOrDefault(ch, 0)+1);
     }
     List<Integer> list = new ArrayList<>();
     for(int cnt: map.values()){
       list.add(cnt);
     }
     return dfs(0, list);
   }
 
   private int dfs(int idx, List<Integer> list){
     if(idx == list.size()){
       int num = 0, den = 1;
       for(int elem: list){
         den *= fact[elem];
         num += elem;
       }
       if(num == 0) return 0;
       return fact[num]/den;
     }
     int res = 0;
     int curr = list.get(idx);
     for(int i=list.get(idx); i>=0; i--){
       list.set(idx, i);
       res += dfs(idx+1, list);
     }
     list.set(idx, curr);
     return res;
   }
}