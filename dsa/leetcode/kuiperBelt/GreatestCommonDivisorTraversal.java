package dsa.leetcode.KuiperBelt;

import java.util.*;

public class GreatestCommonDivisorTraversal {

  /**
   * https://leetcode.com/problems/greatest-common-divisor-traversal/
   * 
   * The idea is to use Union-Find to check if all indices are connected based on the GCD criteria.
   * Prime numbers could be used as well with DFS, but I used a simple factorization approach. 
   * Factorization still boils it down to sqrt(m) operations, since we can generate it's comolementry factor as well within sqrt(m) operations.
   * which was sufficient for the given constraints.
   * 
   * Components are unioned if their GCD is greater than 1, aiming to see if a single connected component can be formed.
   * If a single connected component is formed, then all indices are connected.
   * 
   * TC: O(n*sqrt(m)) SC: O(n), where n is the length of nums, and m is the maximum value in nums.
   * #union-find #graph #number-theory #hard #dfs #prime-numbers #sieve-of-eratosthenes
   */

  
   static List<Integer> primes = null;
   List<Integer> rank;
   List<Integer> parent;
   // private static void generatePrimes(){ // sieve of eratosthenes
   //   if(primes != null) return;
   //   primes = new ArrayList<>();
   //   int []pp = new int[(int)Math.ceil(Math.sqrt((int)1e5+1))];
   //   Arrays.fill(pp, -1);
   //   pp[0] = pp[1] = 1;
   //   for(int i=2; i<pp.length; i++){
   //     if(pp[i] != -1) continue;
   //     pp[i] = 1; primes.add(i);
   //     for(int j=i*2; j<pp.length; j+=i) pp[j] = 0;
   //   }
   // }
  int findSet(int v) {
    if (v == parent.get(v)) return v;
    parent.set(v, findSet(parent.get(v)));
    return parent.get(v);
  }

  private void makeSet(int v) {
    parent.add(v);
    rank.add(0);
  }

  private void unionSet(int a, int b) {
    a = findSet(a);
    b = findSet(b);
    if (a != b) {
      if (rank.get(a) < rank.get(b))
        parent.set(a, b);
      else
        parent.set(b, a);
      if (rank.get(a) == rank.get(b))
        rank.set(a, rank.get(a)+1);
    }
  }

  public Set<Integer> factorize(int num){
    Set<Integer> facts = new HashSet<>();
    int mx = (int)Math.ceil(Math.sqrt(num));
    for(int p=2; p<=mx; p++){
      if(num % p == 0 && num/p != 1) {
        facts.add(p);
        facts.add(num/p);
      }
    }
    return facts;
  }

  public boolean canTraverseAllPairs(int[] nums) {
    // generatePrimes();
    Map<Integer, Integer> map = new HashMap<>();
    parent = new ArrayList<>(nums.length+500);
    rank = new ArrayList<>(nums.length+500);
    int j = 0; boolean mko = false;
    for(int i=0; i<nums.length; i++){
      if(nums[i]==1) { mko = true; continue; }
      if(map.containsKey(nums[i])) continue;
      map.put(nums[i], j);
      makeSet(j++);
    }
    if(mko) {
      if(nums.length>1) return false;
      else return true;
    }
    for(int num:nums) 
      for(int f: factorize(num)){
          if(!map.containsKey(f)){
            map.put(f, j);
            makeSet(j++);
          }
          unionSet(map.get(f), map.get(num));
        }

    j = findSet(0);
    for(int p: parent) 
      if(j != findSet(p)) return false;
    return true;
  }
}