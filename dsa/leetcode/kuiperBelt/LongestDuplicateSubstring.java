package dsa.leetcode.kuiperBelt;

import java.util.HashSet;

public class LongestDuplicateSubstring {
  private static final long PRIME = 29, MOD = (long)(1e9+7);

  public String longestDupSubstring(String s) {
    String res = "";
    int l = 1, r = s.length()-1, mid;
    while (l <= r) {
      mid = (l + r) / 2;
      String checked = check(s, mid);
      if (checked != null) {
        res = checked;
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return res;
  }

  private String check(String s, int size) {
    long H = genHash(s.substring(0, size));
    HashSet<Long> set = new HashSet<>();
    set.add(H);

    long pow = 1;
    for (int i = 1; i < size; i++) pow *= PRIME;

    int n = s.length();
    for (int i = size; i < n; i++) {
      H = nextHash(pow, H, s.charAt(i-size), s.charAt(i));
      if (!set.add(H)) {
        return s.substring(i-size+1, i+1);
      }
    }
    return null;
  }

  private long genHash(String s) {
    long h = 0;
    long a = 1;

    int n = s.length();
    for (int k = n; k >= 1; k--) {
      char ch = s.charAt(k-1);
      h += (ch - 'a' + 1) * a;
      a *= PRIME;
    }
    return h;
  }

  private long nextHash(long pow, long hash, char left, char right) {
    return (hash - (left - 'a' + 1) * pow) * PRIME + (right - 'a' + 1);
  }

  /**
   * URL: https://leetcode.com/problems/longest-duplicate-substring/submissions/
   * This solution utilizes Rabin-Karp algorithm for efficient substring search.
   * It performs binary search on substring length to find the longest duplicating substring.
   * 
   * TC: O(n log n) SC: O(n)
   * #binary-search #sliding-window #rolling-hash #suffix-array #hard
   */
}