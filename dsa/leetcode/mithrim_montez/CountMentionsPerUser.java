package dsa.leetcode.mithrim_montez;

import java.util.*;

public class CountMentionsPerUser {

  /**
   * https://leetcode.com/problems/count-mentions-per-user/description/
   *
   * Sort events by timestamp then process each event.
   * Maintain two sets for offline and online users IDs and calculate mentions.
   *
   * TC: O(n log n) SC: O(n)
   * #array #set #event-simulation #medium
   */

  public int[] countMentions(int numberOfUsers, List<List<String>> events) {
    int[] res = new int[numberOfUsers];
    int[] offline = new int[numberOfUsers];
    Arrays.fill(offline, -1);

    Collections.sort(events, (a, b) -> {
      int A = Integer.valueOf(a.get(1));
      int B = Integer.valueOf(b.get(1));
      if (A == B) {
        return b.get(0).compareTo(a.get(0));
      }
      return A - B;
    });

    for (List<String> eve : events) {
      // Event processing logic goes here
      // Handle MESSAGE and OFFLINE events
    }

    return res;
  }
}