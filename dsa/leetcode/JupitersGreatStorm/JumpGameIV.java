package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/jump-game-iv/description/
 *
 * BFS from the last index, seeding the queue with the second-to-last index and all indices sharing the same value as the last index (all at distance 1). For each dequeued index, enqueue unvisited neighbors via ±1 moves and same-value jumps. The key optimization: after expanding all same-value edges for a value, clear that bucket from the map — ensuring each value group is processed at most once, keeping total edge traversal O(n) rather than O(n²). The BFS distance array serves as both visited marker and answer; memo[0] is returned when the queue drains.
 *
 * TC: O(n) SC: O(n)
 * #array #hash-table #bfs #hard
 */

class JumpGameIV {
    final static int MX = Integer.MAX_VALUE-10000; 
    private boolean []v;
    public int minJumps(int[] arr) {
        int []memo = new int[arr.length];
        v = new boolean[arr.length];
        Arrays.fill(memo, MX);
        memo[memo.length-1] = 0;
        Map<Integer, List<Integer>> mvmap = new HashMap<>();
        for(int i=0; i<arr.length; i++){
            mvmap.putIfAbsent(arr[i], new LinkedList<>());
            mvmap.get(arr[i]).add(i);
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(arr.length-2);
        if(arr.length >= 2) memo[arr.length-2]=1;
        for(int mvidx: mvmap.get(arr[arr.length-1])) {
            if(memo[mvidx] != MX) continue;
            q.offer(mvidx);
            memo[mvidx] = 1;
        }
        while(!q.isEmpty()){
            int idx = q.poll();
            if(idx >= arr.length || idx < 0) continue;
if(idx < arr.length-1 && memo[idx+1] == MX) {
    memo[idx+1] = memo[idx]+1;
    q.offer(idx+1);
}
if(idx > 0 && memo[idx-1] == MX) {
    memo[idx-1] = memo[idx]+1;
    q.offer(idx-1);
}
for(int mvidx: mvmap.get(arr[idx])) {
    if(memo[mvidx] != MX) continue;
    memo[mvidx] = memo[idx]+1;
    q.offer(mvidx);
}
mvmap.get(arr[idx]).clear();
        }
        return memo[0];
    }
}
