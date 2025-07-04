package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class PartitionString {
  /**
   * https://leetcode.com/problems/partition-string/
   *
   * Partition the string by creating unique segments based on previously seen segments.
   * Use a `set` to track seen segments. Start a new segment whenever a repeated segment is found.
   *
   * Could've also used a `Trie` to store segments...
   *
   * TC: O(n^2) SC: O(n)
   * #hash-table #string #trie #simulation #medium
   */
  public List<String> partitionString(String s) {
    List<String> res = new LinkedList<>();
    Set<String> set = new HashSet<>();
    int i = 0;
    while (i < s.length()) {
      int j = i + 1;
      while (j <= s.length() && set.contains(s.substring(i, j))) j++;
      String subs = s.substring(i, j);
      if (set.contains(subs)) {
        res.add(subs);
        set.add(subs);
      }
      i = j;
    }
    return res;
  }
}