package dsa.leetcode.kuiperBelt;

import java.util.*;

public class AlienDictionary {

  /**
   * https://leetcode.com/problems/alien-dictionary/
   *
   * There is a new alien language that uses the English alphabet. However, the order of the
   * letters is unknown to you. You are given a list of strings words from the alien language's
   * dictionary. Now it is claimed that the strings in words are sorted lexicographically by the
   * rules of this new language. If this claim is incorrect, and the given arrangement of string in
   * words cannot correspond to any order of letters, return "". Otherwise, return a string of the
   * unique letters in the new alien language sorted in lexicographically increasing order by the
   * new language's rules. If there are multiple solutions, return any of them.
   *
   * Simply create a graph from the letters and perform a topological sort. If a cycle is detected,
   * return an empty string.
   *
   * TC: O(N * L) SC: O(1)
   * #array #string #dfs #bfs #graph #topological-sort #hard
   */

  public String alienOrder(String[] words) {
    Set<Character> set = new HashSet<>();
    StringBuilder sb = new StringBuilder("");
    if (words.length == 1) {
      for (char c : words[0].toCharArray()) if (!set.contains(c)) {
        set.add(c);
        sb.append(c);
      }
      return sb.toString();
    }
    boolean[][] adjMatrix = new boolean[26][26];
    for (int i = 1; i < words.length; i++) {
      char[] word1 = words[i - 1].toCharArray();
      char[] word2 = words[i].toCharArray();
      for (char ch : word1) set.add(ch);
      for (char ch : word2) set.add(ch);
      int j = 0;
      for (; j < word1.length && j < word2.length; j++) {
        if (word1[j] != word2[j]) {
          if (!adjMatrix[word1[j] - 'a'][word2[j] - 'a']) {
            adjMatrix[word1[j] - 'a'][word2[j] - 'a'] = true;
          }
          break;
        }
      }
      if (j == word2.length && word1.length > word2.length) { // prefix after string check
        return "";
      }
    }
    int[] visited = new int[26];
    for (int i = 0; i < visited.length; i++) {
      if (set.contains((char) ('a' + i)) && !DFS((char) ('a' + i), adjMatrix, visited)) return "";
    }
    return set.isEmpty() ? sb.reverse().toString() : "";
  }

  private boolean DFS(char curr, boolean[][] adjMatrix, int[] visited) {
    if (visited[curr - 'a'] == -1) { // cycle found
      return false;
    }
    if (visited[curr - 'a'] == 1) { // already visited node
      return true;
    }
    visited[curr - 'a'] = -1;
    for (int i = 0; i < 26; i++) {
      if (adjMatrix[curr - 'a'][i] && !DFS((char) ('a' + i), adjMatrix, visited)) return false;
    }
    visited[curr - 'a'] = 1;
    return true;
  }
}