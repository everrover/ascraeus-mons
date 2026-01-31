package dsa.leetcode.Ipestus;

public class MinimumTimeToCompleteAllDeliveries {
  
  /**
   * https://leetcode.com/problems/minimum-time-to-complete-all-deliveries/description/
   *
   * Use binary search over the solution space of time to find the minimum time.
   * 
   * Recharge time exclusively for drone 1 => floor(time / r1)
   * similarly for drone 2 => floor(time / r2)
   * Also, shared time when both drones are not delivering = floor(time / lcm(r1, r2))
   * So shared available time = time - (time / r1) - (time / r2) + (time / lcm(r1, r2)) // inclusion-exclusion
   * 
   * For a given mid time, we can calculate the maximum deliveries each drone can make:
   * drone 1 => ex1 = mid - floor(mid / r1) >= d1
   * drone 2 => ex2 = mid - floor(mid / r2) >= d2
   * combined deliveries => mid - floor(mid / lcm(r1, r2)) >= d1 + d2
   *                     => mid - ex1 - ex2 + shared >= d1 + d2
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