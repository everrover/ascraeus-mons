package dsa.leetcode.fermi_s_paradox;

public class StudentAttendanceRecordI {
  
  /**
   * https://leetcode.com/problems/student-attendance-record-i/
   * 
   * Check for less than 2 absences and no 3 consecutive lates in the given string.
   * 
   * A student is eligible if the string does not contain 'A' more than once and 'L' three times consecutively.
   * 
   * TC: O(n) SC: O(1)
   * #string #easy
   */

  public boolean checkRecord(String s) {
    int ab = 0, lt = 0;
    for (char ch : s.toCharArray()) {
      if (ch == 'P') lt = 0;
      else if (ch == 'A') { lt = 0; ab++; }
      else lt++;
      if (lt >= 3) return false;
      else if (ab >= 2) return false;
    }
    return true;
  }
}