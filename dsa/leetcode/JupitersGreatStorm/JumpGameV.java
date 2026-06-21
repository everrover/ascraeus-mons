package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/jump-game-v/description/
 *
 * DFS with memoization over indices. From index i, scan left and right up to d steps; stop each direction the moment a bar >= arr[i] is encountered (the visibility constraint). Recurse on each reachable j, memoizing the maximum chain length rooted at i. Because you can only jump to strictly shorter bars, the recurrence is acyclic — no index can be revisited on a path — so memoization is correct without cycle detection. Overall: each index is expanded once, each expansion touches O(d) neighbors, giving O(n*d) time and O(n) space.
 *
 * TC: O(n*d) SC: O(n)
 * #array #dp #sorting #hard
 */

class JumpGameV {
    final static int MX = Integer.MAX_VALUE-10000; 
    public int maxJumps(int[] arr, int d) {
        List<int[]> validx = new ArrayList<>();
        for(int i=0; i<arr.length; i++) validx.add(new int[]{arr[i], i});
        Collections.sort(validx, (a,b)->(a[0]-b[0]));
        int []memo = new int[arr.length];
        Arrays.fill(memo, -1);
        int res = 1;
        for(int i=0; i<memo.length; i++){
            res = Math.max(res, dfs(i, arr, memo, d));
        }
        return res;
    }
    private int dfs(int i, int []arr, int []memo, int d){
        if(i<0 || i>arr.length-1) return 0;
        if(memo[i] != -1) return memo[i];
        int res = 1;
        memo[i] = 1;
        final int LL = Math.max(0, i-d);
        final int UL = Math.min(arr.length-1, i+d);
        for(int j=i-1; j>=LL; j--){
            if(arr[j] >= arr[i]) break;
            res = Math.max(res, 1+dfs(j, arr, memo, d));
        }
        for(int j=i+1; j<=UL; j++){
            if(arr[j] >= arr[i]) break;
            res = Math.max(res, 1+dfs(j, arr, memo, d));
        }
        return memo[i] = res;
    }
}
