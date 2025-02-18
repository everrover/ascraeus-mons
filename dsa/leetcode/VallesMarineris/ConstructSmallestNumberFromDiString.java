package dsa.leetcode.VallesMarineris;

public class ConstructSmallestNumberFromDiString {
  
  /**
   * https://leetcode.com/problems/construct-smallest-number-from-di-string/description/?envType=daily-question&envId=2025-02-18
   *
   * Generate permutations until we find the smallest number following the 'I' and 'D' pattern. 
   * Use backtracking to explore all potential numbers.
   *
   * TC: O(n!) SC: O(n)
   * #string #backtracking #stack #greedy #medium
   */
  
   public String smallestNumber(String pattern) {
    boolean []used = new boolean[10];
    for(int i=1; i<10; i++){

      used[i] = true;
      String res = dfs(0, pattern.toCharArray(), ""+i, i, used);
      used[i] = false;
      if(res != null) return res;
    }
    return "";
  }

  private String dfs(int idx, char []pattern, String curr, int prev, final boolean []used){
    if(idx == pattern.length) return curr;
    int inc = pattern[idx]=='D'?-1:1;
    for(int i=prev+inc; i<10 && i>0; i+=inc){
      if(used[i]) continue;
      String newstr = curr + i;
      used[i] = true;
      String res = dfs(idx+1, pattern, newstr, i, used);
      used[i] = false;
      if(res != null) return res;
    }
    return null;
  }
}