package dsa.leetcode.kuiperBelt;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/maximum-hamming-distances/
 * This solution utilizes BFS to explore hamming distances between numbers up to a certain bit length.
 * 
 * It explores all possible variations of a number by flipping each bit and records the shortest distance
 * where a match in the set occurs, implying the maximum hamming distance from the original number.
 * 
 * 2's complements are used for BFS traversal for each number since it allows to find shortest dist via BFS.
 * 
 * P.s. Applied brute-force on a fluke and it worked.
 *
 * TC: O(2^2m=2^m) SC: O(2^m)
 * #bit-manipulation #bfs #array #hard #brute-force
 * [more-opt-code](dsa/leetcode/kuiperBelt/MaxHammingDistances.java)
 */
public class Solution {
  public int[] maxHammingDistances(int[] nums, int m) {
    int []res = new int[nums.length];
    Set<Integer> setOfEle = new HashSet<>();
    Map<Integer, Integer> resmap = new HashMap<>();
    for(int num: nums) setOfEle.add(num);
    for(int i=0; i<nums.length; i++) {
      int num = nums[i];
      if(resmap.containsKey(num)){
        res[i] = resmap.get(num);
      }else{
        res[i] = bfs(setOfEle, num, m);
        resmap.put(num, res[i]);
      }
    }
    return res;
  }

  private int bfs(Set<Integer> v, int num, final int m){
    int mask = (1 << m) - 1;
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> q = new LinkedList<>();
    int comp = ~num & mask;
    q.add(comp);
    visited.add(comp);
    int depth = 0;
    while(!q.isEmpty()){
      depth++;
      int sz = q.size();
      while(sz-- > 0){
        int curr = q.poll();
        if(v.contains(curr)){
          return m-depth+1;
        }
        for(int i=0; i<m; i++){
          int next = curr ^ (1 << i);
          if(!visited.contains(next)){
            visited.add(next); q.add(next);
          }
        }
      }
    }
    return 0;
  }
}
