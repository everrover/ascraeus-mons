package dsa.leetcode.kuiperBelt;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/fancy-sequence/
 * 
 * Design a data structure that simulates an enhanced sequence operation with module 10^9 + 7.
 * Implements methods to append a value, add an increment to all elements, multiply all elements, and retrieve an element by index.
 * 
 * after ith operation the value at index i is (val[i]*mul + add) % mod:
 * add contributes => add = (add + inc) % mod, since get(i) = (val[i]*mul + add + inc) % mod
 * mul contributes => mul = (mul * m) % mod & add = (add * m) % mod, since get(i) = (val[i]*mul + add)*m % mod
 * 
 * but for ith element the already applied operations before it are not to be considered, so inverse mod operation is used to get the 
 * original value when the operations are applied i.e. val[i] = (val[i]*mul + add) % mod
 * 
 * Complexity - TC: append, addAll, multAll - O(1), getIndex - O(1), SC: O(n) due to the list
 * 
 * #math #design #segment-tree #hard #mod-inverse-algo #tricky
 */
public class FancySequence {
  private long mul, add;
  private List<Long> list;
  // private static final int SZ = (int)10e5;
  private static final int SZ = (int)10;
  private static final long MOD = (long)1e9+7;

  // Function to compute the modular inverse using Fermat's Little Theorem
  private long modInverse(long a, long mod) {
    return power(a, mod - 2, mod);
  }

  // Function to compute (x^y) % mod
  private long power(long x, long y, long mod) {
    if (y == 0) return 1;
    long p = power(x, y / 2, mod) % mod;
    p = (p * p) % mod;
    return (y % 2 == 0) ? p : (x * p) % mod;
  }

  public FancySequence() {
    list = new ArrayList<>();
    mul = 1; add = 0;
  }

  public void append(int val) {
    list.add(((MOD + val - add) * modInverse(mul, MOD)) % MOD);
  }

  public void addAll(int inc) {
    add = (add+inc)%MOD;
  }

  public void multAll(int m) {
    add = (add * m)%MOD;
    mul = (mul * m)%MOD;
  }

  public int getIndex(int idx) {
    if(idx >= list.size()) return -1;
    return (int)((list.get(idx)*mul+add)%MOD);
  }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */