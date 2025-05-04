  package dsa.leetcode.VallesMarineris;

import java.util.HashSet;
import java.util.Set;

public class ReportSpamMessage {
  /**
   * https://leetcode.com/problems/report-spam-message/description/
   *
   * Determine if the given message is spam by checking if it contains two or more words that match any word in the bannedWords list.
   * Use a HashSet for efficient look-up and count words matched from the message array.
   * 
   * Could've used binary search as well...
   *
   * TC: O(n + m), where n is the length of the message array and m is the length of the bannedWords array.
   * SC: O(m)
   * #array #hash-table #string #medium
   */

  public boolean reportSpam(String[] message, String[] bannedWords) {
    Set<String> bws = new HashSet<>();
    int c = 0;
    for (var bw : bannedWords) bws.add(bw);
    for (var msg : message) {
      if (bws.contains(msg)) {
        c++;
      }
      if (c > 1) return true;
    }
    return false;
  }
}