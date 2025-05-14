package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

class RecentCounter {
  /**
   * https://leetcode.com/problems/number-of-recent-calls/description/?envType=problem-list-v2&envId=design
   *
   * We maintain a list to keep track of the timestamps within the past 3000 milliseconds. Each
   * time we add a new time, we remove the timestamps older than `t - 3000` to ensure the list
   * only contains relevant timestamps within the window.
   *
   * TC: O(n) SC: O(n)
   * #queue #design #easy
   */

  private List<Integer> requests;
  private final static int FIX_WINDOW = 3000;

  public RecentCounter() {
    requests = new ArrayList<>();
  }

  public int ping(int t) {
    int target = t - FIX_WINDOW;
    requests.add(t);
    // Remove requests that are older than the current window
    while (requests.get(0) < target) {
      requests.remove(0);
    }
    return requests.size();
  }
}