package dsa.leetcode.KuiperBelt;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-the-number-of-good-pairs-ii/
 * The algorithm calculates the number of good pairs by creating mappings of nums1 elements and
 * nums2 elements multiplied by k, tracking their frequencies. It then iterates through these
 * mappings to calculate the total number of good pairs based on divisibility and frequency.
 * 
 * TC: O(m*log(m))  = m * (n+n/2+n/3+n/4+ ... +1), where n and m max(nums1) and len(nums2) respectively, harmonic series tops to log(n)
 * SC: O(n + m) for storing elements in hash maps
 * #array #hash-table #medium #factorization
 */
public class NumberOfGoodPairsII {

  public long numberOfPairs(int[] nums1, int[] nums2, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Integer> map2 = new HashMap<>();
    long max = Long.MIN_VALUE;
    for(int num: nums1){
      map.putIfAbsent(num, 0);
      map.put(num, map.get(num)+1);
      max = Math.max(max, num);
    }
    long res = 0;
    for(int i=0; i<nums2.length; i++){
      int x = nums2[i] * k;
      map2.putIfAbsent(x, 0);
      map2.put(x, map2.get(x)+1);
    }
    for(Map.Entry<Integer, Integer> me: map2.entrySet()){
      int x = me.getKey(), y = me.getValue();
      int z = x;
      while(z <= max){
        if(map.containsKey(z)) {
          res = res + ((long)y*(long)map.get(z));
        }
        z += x;
      }
    }
    return res;
  }
}
