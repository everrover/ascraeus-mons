package dsa.leetcode.EuropasOceanWorld;

import java.util.HashMap;
import java.util.Map;

public class LoggerRateLimiter {
  
  /**
   * https://leetcode.com/problems/logger-rate-limiter/description/?envType=weekly-question&envId=2025-05-08
   *
   * The goal is to design a system that only allows identical messages to be printed if at least 10 seconds have passed since the last print of that message.
   * This can be easily accomplished by using a hashmap to track the last printed timestamp of each message.
   * If a message should be printed, update its timestamp in the hashmap.
   *
   * TC: O(1) SC: O(n)
   * #hash-table #design #data-stream #easy
   */

  private Map<String, Integer> messageTimestampMap;
  
  public LoggerRateLimiter() {
    messageTimestampMap = new HashMap<>();
  }

  public boolean shouldPrintMessage(int timestamp, String message) {
    // Check if the message should be printed
    if (!messageTimestampMap.containsKey(message) || timestamp - messageTimestampMap.get(message) >= 10) {
      // Update the last printed timestamp of the message
      messageTimestampMap.put(message, timestamp);
      return true;
    }
    return false;
  }

  /**
   * Your Logger object will be instantiated and called as such:
   * Logger obj = new Logger();
   * boolean param_1 = obj.shouldPrintMessage(timestamp, message);
   */
}