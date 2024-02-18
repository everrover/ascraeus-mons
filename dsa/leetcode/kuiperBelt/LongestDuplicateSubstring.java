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
   * It performs binary search on substring length(the entire solution space) to find the longest duplicating substring.
   * 
   * ROBIN-KARP ALGORITHM:
   * 1. It is a string searching algorithm that uses hashing to find any one of a set of pattern strings in a text.
   * 2. It is a probabilistic algorithm that uses hashing to find any one of a set of pattern strings in a text.
   * 
   * For a string of length n, the algorithm can find a pattern of length m in O(n+m) time. The pattern is hashed and 
   * compared with the hash of the substring of the same length. 
   * 
   * Theoretically, for str[0,m-1] initial hash is calculated and then for str[1,m] the hash is calculated using the previous hash:
   * 
   * initial-hash(str[0,m-1]) = str[0] + prime * str[1] + prime^2 * str[2] + ... + prime^(m-1) * str[m-1]
   * new-hash(str[i,i+m-1]) = (hash - str[i-1]) + prime^(m-1) * str[i+m-1]
   * 
   * Although for current problem, I used which is much more optimal:
   * 
   * initial-hash(str[0,m-1]) = str[m-1] + prime * str[m-2] + prime^2 * str[m-3] + ... + prime^(m-1) * str[0]
   * new-hash(str[i,i+m-1]) = (hash - str[i] * prime^(m-1)) * prime + str[i+m-1]
   * 
   * HASH COLLISIONS:
   * 1. The algorithm uses a prime number to reduce the chance of hash collisions. 
   * 2. If hash is found to exist, the algorithm compares the patterns matching the hash with the substring to avoid false positives.
   * 
   * TC: O(n log n) SC: O(n)
   * #binary-search #sliding-window #rolling-hash #suffix-array #hard #binary-search-over-solution-space
   */
}