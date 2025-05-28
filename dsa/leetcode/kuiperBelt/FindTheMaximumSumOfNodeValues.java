package dsa.leetcode.KuiperBelt;

public class FindTheMaximumSumOfNodeValues {

  /**
   * https://leetcode.com/problems/find-the-maximum-sum-of-node-values/submissions/
   * The approach involves iterating over the nums array and for each number, calculating the XOR with k.
   * It keeps track of the maximum sum we can achieve, and adjusts based on the count of nums where XOR operation increases the value.
   * This solution leverages the property of XOR to increase the total sum selectively.
   * 
   * Three crucial observations:
   * 1. a^k^k = a^(k^k) = a
   * 2. Due to #1 and tree like nature of setup, we can select any two nodes and perform XOR operation on them.
   * It propagates from node#1 to top, then from top to node#2. Hence all xor operations nullify due to #1 on 
   * the path except the nodes#1 and #2.
   * 3. In case of even number of XOR operations(i.e. a^k > a), we can find the 
   * => sum = sum(max(a, a^k))
   * 4. In case of odd number of XOR operations i.e. for one of the nodes we need to subtract the value. 
   * => except = min(abs(a-a^k))
   *
   * ------------
   *
   * Another way for intuition. Pick any node. From this node we can reach all other nodes and attempt
   * to perform XOR operation on the terminal node. This can be done greedily, only if x^k > x, it's picked otherwise it'll decrease the result.
   *
   * Consider path from a till f
   * > a - b - c - d - e - f
   * If all edges are XORed with k, for intermediate nodes, the value will be x ^ k ^ k = x
   * For a and f, the value will be a^k and f^k respectively.
   *
   * > a - b - c - d - e - f
   *             - g - h
   * Here for c, it's c^k^k^k = c^k, a => a^k = a, f => f^k, h => h^k, rest stay the same.
   * What if we want to XOR only f? We'll have to start from somewhere and end at f. So atleast one more node would XORed.
   * What if we want to XOR only f and g?
   * i.e. if XORs on a given number are even, they are nullified, else they are not.
   *
   * On any given node, if a value on a node is XORed with k, to maintain an even/odd number of XORs on current nodes the child
   * nodes must be XORed odd/even number of times, respectively.
   *
   * This we can do recursively for all nodes, and then sum up the values.
   *
   * ------------------
   * 
   * p.s. in case of arbitrary graphs, the approach will be wildly different.
   *
   * TC: O(n) SC: O(1)
   * #array #greedy #bit-manipulation #tree #min-tracking #math #hard
   */
  public long maximumValueSum(int[] nums, int k, int[][] edges) {
    long sum = 0, except = Integer.MAX_VALUE, cnt = 0;
    for(int num: nums){
      int newnum = num^k;
      int val = Math.max(newnum, num);
      sum += val;
      if(newnum > num) cnt++;
      except = Math.min(except, Math.abs(num-(num^k)));
    }
    return cnt%2==0?sum:(sum-except);
  }
}