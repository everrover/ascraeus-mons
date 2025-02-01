package dsa.leetcode.mithrim_montez;

class Solution {
  /**
   * https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/description/
   *
   * Use a sliding window approach to maximize free time by rescheduling up to k meetings maintaining their duration but changing their start times, ensuring meetings remain non-overlapping.
   *
   * TC: O(n) SC: O(1)
   * #sliding-window #interval-scheduling #greedy #medium
   */

  public int maxFreeTime(int eventTime, int k, int[] st, int[] en) {
    if(st.length == 1) return eventTime - st[0] + en[0];
    final int n = st.length;
    int left = 0, right = 0, meetTime = 0, res = 0, time = 0;
    while(right < n){
      right = Math.max(right, left);
      while(right < n && right - left < k){
        meetTime += en[right] - st[right];
        right++;
      }
      if(right == n){
        res = Math.max(res, eventTime - meetTime - time);
        break;
      }
      res = Math.max(res, st[right] - meetTime - time);
      time = en[left];
      meetTime -= en[left] - st[left];
      left++;
    }
    return res;
  }
}