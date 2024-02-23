package dsa.leetcode.kuiperBelt;

import java.util.*;

public class MostFrequentPrime {

  /**
   * https://leetcode.com/problems/most-frequent-prime/
   * The function computes the most frequent prime number from all possible numbers created by traversing the matrix.
   * Utilizes pre-computation of prime numbers and iterates through each cell generating numbers based on possible moves.
   * 
   * TC: O(n*m*8^max(n,m)) SC: O(prime numbers till 1e6)
   * #array #dfs #recursion #prime-check #medium #sieve-of-eratosthenes
   */
  
  private static int LT = (int)1e6;
  private static Set<Integer> primes = new HashSet<>();
  
  public int mostFrequentPrime(int[][] mat) {
    if(primes.isEmpty()) computePrimes();
    final int m = mat.length, n = mat[0].length;
    Map<Integer, Integer> map = new HashMap<>();
    int res = -1, cnt = 0;
    int [][]moves = new int[][]{{0,1},{1,0},{1,1},{0,-1},{-1,0},{-1,1},{1,-1},{-1,-1}};
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        for(int []move: moves){
          int r = i+move[0], c = j+move[1];
          var num=mat[i][j];
          while(r>=0 && c>=0 && r<m && c<n){
            num = num*10+mat[r][c];
            r+=move[0];
            c+=move[1];
            if(primes.contains(num)){
              map.putIfAbsent(num, 0);
              int ncnt = map.get(num)+1;
              map.put(num, ncnt);
            }
          }
        }
      }
    }
    
    for(Map.Entry<Integer, Integer> f: map.entrySet()){
      if(f.getValue()>cnt){
        res = f.getKey();
        cnt = f.getValue();
      }else if(f.getValue() == cnt){
        res = Math.max(f.getKey(), res);
        cnt = f.getValue();
      }
    }
    return res;
  }
  
  private void computePrimes(){
    int UB = (int) Math.sqrt(LT);
    boolean prime[] = new boolean[LT+1];
    Arrays.fill(prime, true);
    for (int m = 2; m <= UB; m++) {
      if (prime[m]) {
        for (int k = m*m; k<=LT; k += m) prime[k] = false;
      }
    }
    for(int p=1; p<prime.length; p++) if(prime[p]) primes.add(p);
  }
}