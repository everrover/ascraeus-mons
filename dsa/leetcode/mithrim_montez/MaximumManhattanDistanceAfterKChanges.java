package dsa.leetcode.mithrim_montez;

public class MaximumManhattanDistanceAfterKChanges {

    /**
     * https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes/description/
     *
     * You can apply up to k changes to maximize the Manhattan distance in any chosen direction.
     * This algorithm keeps track of how many steps are taken in each direction and performs character changes to optimize the result.
     * 
     * Since the entire path is considered, we have to find the maximum Manhattan distance at each step.
     * If max amonst N and S is picked, we can greedily pick and swap it with the other direction, to a maximum of k times.
     * If we perform `tod` swaps, we can increase the result by 2*tod.
     * 
     * Similarly, if max amongst E and W is picked, we can greedily pick and swap it with the other direction, to a maximum of k times.
     * 
     * We can swap with the other orientation [N->W] or [E->S] to attempt, maximizing the Manhattan distance, but it'll only 
     * increase the result by 1, so such ops are not beneficial.
     *
     * TC: O(n) SC: O(1)
     * #greedy #string #manhattan-distance #medium
     */

    public int maxManhattanDistance(String str, int k) {
      int n = 0, e = 0, s = 0, w = 0, res = 0;
      int kt = k, tod = 0;
      for(char ch: str.toCharArray()){
        if(ch == 'N') n++;
        else if(ch == 'W') w++;
        else if(ch == 'S') s++;
        else if(ch == 'E') e++;
        if(n>s) {
          tod += Math.min(s, kt);
          kt -= Math.min(s, kt);
        }else{      
          tod += Math.min(n, kt);
          kt -= Math.min(n, kt);
        }
        if(e>w) {
          tod += Math.min(w, kt);
          kt -= Math.min(w, kt);
        }else{      
          tod += Math.min(e, kt);
          kt -= Math.min(e, kt);    
        }
        // System.out.println(e+":"+w+":"+s+":"+n+"::"+tod+":"+k);
        res = Math.max(Math.abs(n-s)+Math.abs(e-w)+2*tod, res);
    }
    return res;
  }
}