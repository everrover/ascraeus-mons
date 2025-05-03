package dsa.leetcode.KuiperBelt;

import java.util.*;

public class AlienDictionary {

  /**
   * https://leetcode.com/problems/alien-dictionary/
   *
   * To determine the order of letters in the given alien language, we create a graph where each node represents a letter. We then add directed edges based on the given order of letters in successive words. Perform a topological sort using Depth-First Search (DFS) to find a possible order.
   *
   * TC: O(C) where C is the total number of characters in all words combined
   * SC: O(1) since extra space depends on the number of unique characters
   * #graph #topological-sort #dfs #string #hard
   */

  boolean [][]adjMatrix;
  StringBuilder sb;
  Set<Character> set;
  int visited[];
  public String alienOrder(String[] words) {
    set = new HashSet<>();
    sb = new StringBuilder("");
    if(words.length==1){
      for(char c: words[0].toCharArray()) if(!set.contains(c)){
        set.add(c); sb.append(c);
      }
      return sb.toString();
    }
    adjMatrix = new boolean[26][26];
    for(int i=1; i<words.length; i++){
      char[] word1 = words[i-1].toCharArray();
      char[] word2 = words[i].toCharArray();
      for(char ch: word1) set.add(ch);
      for(char ch: word2) set.add(ch);
      int j=0;
      for(; j<word1.length&&j<word2.length; j++){
        if(word1[j] != word2[j]){
          if(!adjMatrix[word1[j]-'a'][word2[j]-'a']){
            adjMatrix[word1[j]-'a'][word2[j]-'a'] = true;
          }
          break;
        }
      }
      if(j==word2.length && word1.length>word2.length){ // prefix after string check
        return "";
      }
    }
    visited = new int[26];
    for(int i=0; i<visited.length; i++){
      if(set.contains((char)('a'+i)) && !DFS((char)('a'+i)) ) return "";
    }
    return set.isEmpty()? sb.reverse().toString(): "";
  }

  private boolean DFS(char curr){
    if(visited[curr-'a'] == -1){ // cycle found
      return false;
    }
    if(visited[curr-'a'] == 1){ // already visited node
      return true;
    }
    visited[curr-'a'] = -1;
    for(int i=0; i<adjMatrix.length; i++){
      if(set.contains((char)('a'+i)) && adjMatrix[curr-'a'][i] && !DFS((char)('a'+i)) ) {
        return false;
      }
    }
    visited[curr-'a'] = 1;
    set.remove(curr);
    sb.append(curr);
    return true;
  }
}