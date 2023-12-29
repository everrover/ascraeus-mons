                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  package dsa.leetcode.kuiperBelt;

import java.util.ArrayList;
import java.util.List;

public class LastVisitedInts {
  /**
   * https://leetcode.com/problems/last-visited-integers/
   * 
   * As simple as it gets. Just keep track of all the last visited integers.
   * 
   * TC: O(n) SC: O(n)
   * #easy #brute #contest
   */
  public List<Integer> lastVisitedIntegers(List<String> words) {
    ArrayList<Integer> ans = new ArrayList<>();
    ArrayList<Integer> integers = new ArrayList<>();
    int k = 0;
    for (String word : words) {
      if (word.equals("prev")) {
        k++;
        int n = integers.size();
        int val = k <= n ? integers.get(n - k) : -1;
        ans.add(val);
      } else {
        k = 0;
        integers.add(Integer.valueOf(word));
      }
    }
    return ans;
  }
}
