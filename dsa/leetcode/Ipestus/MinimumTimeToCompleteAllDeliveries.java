package dsa.leetcode.Ipestus;

public class MinimumTimeToCompleteAllDeliveries {
  
  /**
   * https://leetcode.com/problems/minimum-time-to-complete-all-deliveries/description/
   *
   * Use binary search to find the minimum total time required for all drone deliveries,
   * accounting for each drone's recharge interval. Consider effective working time
   * and shared hours where neither drone can work.
   *
   * TC: O(log(max_deliveries)) SC: O(1)
   * #binary-search #math #medium
   */

  public long minimumTime(int[] d, int[] r) {
      long d1 = d[0], d2 = d[1], r1 = r[0], r2 = r[1];
      long low = 0, high = (long)(1e10) + 1, mid;
      long gcdd = gcd(r1, r2);
      long lcm = (r1 / gcdd) * r2;
      while (low <= high) {
          mid = (low + high) / 2;
          long ex1 = mid - mid / r1;
          long ex2 = mid - mid / r2;
          // long shared = mid - (mid / r1) - (mid / r2) + (mid / lcm);
          if (ex1 >= d1 && ex2 >= d2 && mid - (mid / lcm) >= d1 + d2) {
              high = mid - 1;
          } else {
              low = mid + 1;
          }
      }
      return low;
  }
  
  private long gcd(long a, long b) {
      while (b != 0) {
          long temp = b;
          b = a % b;
          a = temp;
      }
      return a;
  }
}