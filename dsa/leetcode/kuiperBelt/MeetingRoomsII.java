package dsa.leetcode.KuiperBelt;

import java.util.Arrays;

public class MeetingRoomsII {

  /**
   * https://leetcode.com/problems/meeting-rooms-ii/
   *
   * To determine the minimum number of meeting rooms required, we first convert each interval's start and end times into an array of custom 
   * objects (Tm). We then sort these objects by time, prioritizing start times over end times in case of ties. We maintain a counter to track 
   * the number of concurrent meetings and continuously update the maximum number of concurrent meetings found.
   * 
   * TC: O(n log n) SC: O(n)
   * #array #two-pointers #greedy #sorting #heap #priority-queue #medium
   */

  private class Tm {
    public int time;
    public boolean isStart;
  }

  public int minMeetingRooms(int[][] intervals) {
    Tm[] tms = new Tm[intervals.length * 2];
    for (int i = 0; i < intervals.length; i++) {
      int[] interval = intervals[i];
      tms[i * 2] = new Tm(); tms[i * 2].time = interval[0]; tms[i * 2].isStart = true;
      tms[i * 2 + 1] = new Tm(); tms[i * 2 + 1].time = interval[1]; tms[i * 2 + 1].isStart = false;
    }
    Arrays.sort(tms, (a, b) -> (a.time == b.time ? (!a.isStart ? -1 : 1) : (a.time - b.time)));
    int ans = 0, cnt = 0;
    for (Tm tm : tms) {
      if (tm.isStart) {
        cnt++;
      } else {
        cnt--;
      }
      ans = Integer.max(ans, cnt);
    }
    return ans;
  }
}