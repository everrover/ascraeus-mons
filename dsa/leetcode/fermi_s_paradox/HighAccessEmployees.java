package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class HighAccessEmployees {

  /**
   * https://leetcode.com/problems/high-access-employees/
   *
   * The solution identifies 'high-access' employees by examining access times
   * sorted for each employee. It checks if at least three accesses occur
   * within any one-hour window without wrapping to the next hour.
   *
   * TC: O(n log n) due to sorting and O(n^2) for checking each pair of times,
   * where n is the number of accesses.
   * SC: O(n) used for storing employee access lists.
   *
   * #array #hash-table #string #sorting #medium
   */

  public List<String> findHighAccessEmployees(List<List<String>> accessTimes) {
    Map<String, List<String>> map = new HashMap<>();
    for (List<String> at : accessTimes) {
      map.putIfAbsent(at.get(0), new ArrayList<>());
      map.get(at.get(0)).add(at.get(1));
    }

    List<String> res = new LinkedList<>();
    for (Map.Entry<String, List<String>> e : map.entrySet()) {
      Collections.sort(e.getValue());
      if (isHighAccess(e.getValue())) {
        res.add(e.getKey());
      }
    }
    return res;
  }

  private boolean isHighAccess(List<String> access) {
    int l = 0, r = 0, res = 0;
    while (l <= r && r < access.size()) {
      while (r < access.size() && diff(access.get(l), access.get(r)) <= 60) {
        res = Math.max(res, r - l + 1);
        r++;
      }
      l++;
    }
    return res >= 3;
  }

  private int diff(String a, String b) {
    int hh1 = Integer.valueOf(a.substring(0, 2));
    int hh2 = Integer.valueOf(b.substring(0, 2));
    int mm1 = Integer.valueOf(a.substring(2, 4));
    int mm2 = Integer.valueOf(b.substring(2, 4));
    if (hh1 == hh2) {
      return mm2 - mm1;
    } else if (hh2 - hh1 == 1) {
      return mm2 + 60 - mm1;
    } else {
      return 61;
    }
  }
}